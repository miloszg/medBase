using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows;
using System.Windows.Input;
using System.Drawing;
using System.Windows.Media.Imaging;
using System.IO;
using Microsoft.Win32;

namespace DesktopApplication
{
    public class GenerateReceiptViewModel : BaseViewModel
    {
        public ICommand goBackCommand { get; set; }
        public ICommand submitCommand { get; set; }
        public ICommand addMedicamentCommand { get; set; }
        public ICommand removeMedFromListCommand { get; set; }
        public ICommand generateReceiptCommand { get; set; }

        public SearchMedsWindow medsWindow { get; set; }
        public string patientName { get; set; }
        public string patientAgeLabel { get; set; }
        public string patientSexLabel { get; set; }
        public string patientMail { get; set; }
        public string medNameTextBox { get; set; }
        public string commentTextBox { get; set; }
        public bool isGenerateQrCode { get; set; }
        public bool isSendToMail { get; set; }
        public List<Medicament> patientReceiptMedsList { get; set; }

        public MedsViewModel medsViewModel;

        private User patient = IoC.Get<ApplicationWindowViewModel>().Patient;
        public static List<Medicament> staticPatientReceiptMedsList { get; set; }
        public Image QrImage { get; set; }

        public GenerateReceiptViewModel()
        {
            this.goBackCommand = new RelayCommand(() => GoBackCommand());
            this.submitCommand = new RelayCommand(() => SubmitCommand());
            this.generateReceiptCommand = new RelayCommand(() => GenerateReceiptCommand());
            this.addMedicamentCommand = new RelayCommand(() => AddMedicamentCommand());
            this.removeMedFromListCommand = new RelayCommandWithParameters((parameter) => RemoveMedFromListCommand(parameter));
            this.patientName = $"{patient.first_name} {patient.last_name}";
            this.patientAgeLabel = patient.age.ToString();
            this.patientSexLabel = patient.sex;
            this.patientMail = patient.mail;
            this.patientReceiptMedsList = new List<Medicament>();
        }

        private void GenerateReceiptCommand()
        {
            string filePath = null;
            if(isGenerateQrCode)
            {
                this.GenerateQrCode();
            }
            SaveFileDialog saveFileDialog = new SaveFileDialog();
            saveFileDialog.Filter = "PDF file (*.pdf)|*.pdf";
            if (saveFileDialog.ShowDialog() == true)
            {
                filePath = saveFileDialog.FileName;
            }
            if(filePath == null)
            {
                MessageBox.Show("Brak podanej sciezki zapisu pliku.");
                return;
            }
            ReceiptPDFCreator pdfCreator = new ReceiptPDFCreator(this.patientReceiptMedsList, commentTextBox ,QrImage);
            pdfCreator.GeneratePDF(filePath);
        }

        private void GenerateQrCode()
        {
            string medsNames = CreateNamesMedsString(this.patientReceiptMedsList);

            Zen.Barcode.CodeQrBarcodeDraw qrCode = Zen.Barcode.BarcodeDrawFactory.CodeQr;
            QrImage = qrCode.Draw(medsNames, 50);

            string filePath = "qrcode.png";
            QrImage.Save(filePath);
        }

        private string CreateNamesMedsString(List<Medicament> patientReceiptMedsList)
        {
            string medsNames = "";
            foreach (var medication in patientReceiptMedsList)
            {
                medsNames += $"{medication.Name},";
            }
            medsNames.Remove(medsNames.Length - 1);
            return medsNames;
        }

        private void RemoveMedFromListCommand(object parameter)
        {
            var medsName = parameter as string;

            this.patientReceiptMedsList.Remove(this.patientReceiptMedsList.Find((meds) => meds.Name == medsName));
            this.patientReceiptMedsList = this.RefreshPatientReceiptList();
        }

        private void AddMedicamentCommand()
        {
            this.medsViewModel = new MedsViewModel();
            this.medsViewModel.EnableAddingToReceipt();
            this.medsWindow?.Close();
            this.medsWindow = new SearchMedsWindow(medsViewModel);
            this.medsViewModel.ReceiptMedsListGenerated += this.RaiseReceiptListChange;
            this.medsWindow?.Show();
        }

        public void RaiseReceiptListChange(object sender, EventArgs e)
        {
            foreach (var item in this.medsViewModel.patientReceiptMedsList)
            {
                if(!this.patientReceiptMedsList.Any((x)=> x.Name == item.Name))
                {
                    this.patientReceiptMedsList.Add(item);
                }
            }
            this.patientReceiptMedsList = this.RefreshPatientReceiptList();
        }

        private List<Medicament> RefreshPatientReceiptList()
        {
            List<Medicament> foundMedsList = new List<Medicament>();

            foreach (var medication in this.patientReceiptMedsList)
            { 
                foundMedsList.Add(medication);
            }
            return foundMedsList;
        }

        public void SubmitCommand()
        {
            MessageBox.Show("Recepta zostala wygenerowana!");
            IoC.Get<ApplicationWindowViewModel>().currentPage = ApplicationPage.SubmitPatient;
        }

        private void GoBackCommand()
        {
            IoC.Get<ApplicationWindowViewModel>().currentPage = ApplicationPage.SubmitPatient;
        }
    }
}
