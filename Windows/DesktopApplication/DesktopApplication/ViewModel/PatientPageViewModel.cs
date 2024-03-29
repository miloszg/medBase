﻿using System;
using System.Collections.Generic;
using System.Windows;
using System.Windows.Input;

namespace DesktopApplication
{
    public class PatientPageViewModel : BaseViewModel
    {
        public string PatientCode {get; set;}

        public ICommand goBackCommand { get; set; }
        public ICommand submitCommand { get; set; }

        public PatientPageViewModel()
        {
            this.goBackCommand = new RelayCommand(() => GoBackCommand());
            this.submitCommand = new RelayCommand(() => SubmitCommand());
        }

        private void SubmitCommand()
        {
            //if (true)
            //{
            //    DatabaseRetriever databaseRetrieverTest = new DatabaseRetriever(IoC.Get<ApplicationWindowViewModel>().databaseManager);
            //    this.SetPatient("3", databaseRetrieverTest);
            //    IoC.Get<ApplicationWindowViewModel>().currentPage = ApplicationPage.SubmitPatient;
            //    return;
            //}

            if (!Int32.TryParse(this.PatientCode, out int result))
            {
                MessageBox.Show("Nieprawidlowy kod pacjenta!");
                return;
            }
            
            DatabaseRetriever databaseRetriever = new DatabaseRetriever(IoC.Get<ApplicationWindowViewModel>().databaseManager);
            string patientID = this.GetPatientID(databaseRetriever);
            if(patientID == null)
            {
                return;
            }
            bool Status = this.SetPatient(patientID, databaseRetriever);

            if(Status)
            {
                IoC.Get<ApplicationWindowViewModel>().currentPage = ApplicationPage.SubmitPatient;
            }
        }

        private string GetPatientID(DatabaseRetriever databaseRetriever)
        {
            string patientID = null;
            var codeData = databaseRetriever.GetFromDatabase("pacjent_kod",
                            new List<string>()
                            {
                            "pacjent_id",
                            },
                            $"WHERE pacjent_kod = {this.PatientCode}",
                            rowsToGet: 1);

            if (codeData.Count != 1)
            {
                MessageBox.Show("Nieprawidlowy kod pacjenta!");
                return patientID;
            }

            patientID = codeData[0];
            return patientID;
        }

        public void GoBackCommand()
        {
            IoC.Get<ApplicationWindowViewModel>().currentPage = ApplicationPage.MainMenu;
        }

        public bool SetPatient(string patientID, DatabaseRetriever databaseRetriever)
        {
            bool Status = true;
            var patientData = databaseRetriever.GetFromDatabase("pacjent",
                new List<string>()
                {
                    "id","nazwa_uzytkownika", "imie","nazwisko","email","data_urodzenia","plec"
                },
                $"WHERE id={patientID}");

            User patient = null;
            if (patientData.Count == 6)
            {
                patient = new User(Int32.Parse(patientData[0]), patientData[1], patientData[2], patientData[3], patientData[4], patientData[5]);
            }
            else if (patientData.Count == 7)
            {
                patient = new User(Int32.Parse(patientData[0]), patientData[1], patientData[2], patientData[3], patientData[4], Convert.ToDateTime(patientData[5]), patientData[6]);
            }
            else
            {
                MessageBox.Show("Error with patient data from MySQL!");
                return false;
            }
            IoC.Get<ApplicationWindowViewModel>().SetPatient(patient);
            return Status;
        }

    }
}