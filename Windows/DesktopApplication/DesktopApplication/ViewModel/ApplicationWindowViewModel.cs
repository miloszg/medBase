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
    public class ApplicationWindowViewModel : BaseViewModel
    {
        public ICommand medsCommand { get; set; }
        public ICommand patientCommand { get; set; }
        public ApplicationPage currentPage { get; set; }

        public User Doctor { get; private set; }
        public User Patient { get; private set; }

        public MedsViewModel medsViewModel;

        public DatabaseManager databaseManager { get; private set; }

        public ApplicationWindowViewModel()
        {
            currentPage = ApplicationPage.MainMenu;
            this.databaseManager = new DatabaseManager("localhost", "leki", "admin", "admin");
            this.medsCommand = new RelayCommand(() => MedsCommand());
            this.patientCommand = new RelayCommand(() => PatientCommand());
        }

        public void SetDoctor(User user)
        {
            this.Doctor = user;
        }
        public void SetPatient(User user)
        {
            this.Patient = user;
        }

        public void MedsCommand()
        {
            this.currentPage = ApplicationPage.Meds;
        }

        public void PatientCommand()
        {
            this.currentPage = ApplicationPage.Patient;
        }

        public void GenerateQRCommand()
        {
            this.currentPage = ApplicationPage.GenerateReceipt;
        }

    }
}
