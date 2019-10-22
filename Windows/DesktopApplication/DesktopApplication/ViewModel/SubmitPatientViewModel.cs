using Renci.SshNet.Messages;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;
using GalaSoft.MvvmLight.Messaging;
using System.Windows;
using Ninject;

namespace DesktopApplication
{
    public class SubmitPatientViewModel : BaseViewModel
    {
        public AddNoteWindow noteWindow { get; set; }
        public ICommand goBackCommand { get; set; }
        public ICommand generateReceiptCommand { get; set; }
        public ICommand openNoteWindowCommand { get; set; }
        public ICommand saveNoteCommand { get; set; }

        public string patientAgeLabel { get; set; }
        public string patientSexLabel { get; set; }
        public string notesTitleTextBox { get; set; }
        public string notesContentTextBox { get; set; }

        public List<Medicament> patientMedsList { get; set; }
        public List<string> patientStoryList { get; set; }

        public SubmitPatientViewModel()
        {
            this.goBackCommand = new RelayCommand(() => GoBackCommand());
            this.generateReceiptCommand = new RelayCommand(() => GenerateReceiptCommand());
            this.openNoteWindowCommand = new RelayCommand(() => OpenNoteWindowCommand());
            this.saveNoteCommand = new RelayCommand(() => SaveNoteCommand());
            this.patientAgeLabel = "42";
            this.patientSexLabel = "Mezczyzna";
        }

        private void SaveNoteCommand()
        {
            //TODO INSERT TO DATABASE NOTE ABOUT PATIENT'S VISIT 
            noteWindow.Close();
        }

        private void OpenNoteWindowCommand()
        {
            noteWindow = new AddNoteWindow(this);
            noteWindow.Show();
        }

        private void GenerateReceiptCommand()
        {
            IoC.Get<ApplicationWindowViewModel>().currentPage = ApplicationPage.GenerateReceipt;
        }

        private void GoBackCommand()
        {
            IoC.Get<ApplicationWindowViewModel>().currentPage = ApplicationPage.Patient;
        }
    }
}
