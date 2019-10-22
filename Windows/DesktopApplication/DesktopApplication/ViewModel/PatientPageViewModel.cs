using System;
using System.Windows.Input;

namespace DesktopApplication
{
    public class PatientPageViewModel : BaseViewModel
    {
        public string patientCode {get; set;}

        public ICommand goBackCommand { get; set; }
        public ICommand submitCommand { get; set; }

        public PatientPageViewModel()
        {
            this.goBackCommand = new RelayCommand(() => GoBackCommand());
            this.submitCommand = new RelayCommand(() => SubmitCommand());
        }

        private void SubmitCommand()
        {
            //somtehing
            IoC.Get<ApplicationWindowViewModel>().currentPage = ApplicationPage.SubmitPatient;
        }

        public void GoBackCommand()
        {
            IoC.Get<ApplicationWindowViewModel>().currentPage = ApplicationPage.MainMenu;
        }

    }
}