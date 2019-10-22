using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Input;

namespace DesktopApplication
{
    public class GenerateReceiptViewModel : BaseViewModel
    {
        public ICommand goBackCommand { get; set; }
        public ICommand submitCommand { get; set; }

        public string notesTextBox { get; set; }
        public string patientNameTextBox { get; set; }
        public string medNameTextBox { get; set; }
        public bool isGenerateQrCode { get; set; }
        public bool isSendToMail { get; set; }

        public GenerateReceiptViewModel()
        {
            this.goBackCommand = new RelayCommand(() => GoBackCommand());
            this.submitCommand = new RelayCommand(() => SubmitCommand());
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
