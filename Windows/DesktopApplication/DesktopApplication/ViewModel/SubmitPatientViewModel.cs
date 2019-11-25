using System;
using System.Collections.Generic;
using System.Windows.Input;

namespace DesktopApplication
{
    public class SubmitPatientViewModel : BaseViewModel
    {
        public AddNoteWindow noteWindow { get; set; }
        public ShowNoteWindow showNoteWindow { get; set; }
        public MedicationWindow medicationWindow{ get; set; }
        public ICommand goBackCommand { get; set; }
        public ICommand goToGenerateReceipt { get; set; }
        public ICommand openNoteWindowCommand { get; set; }
        public ICommand showNoteCommand { get; set; }
        public ICommand showMedCommand { get; set; }
        public ICommand saveNoteCommand { get; set; }
        public string patientName{ get; set; }
        public string patientAgeLabel { get; set; }
        public string patientSexLabel { get; set; }
        public string patientMail { get; set; }
        public string notesTitleTextBox { get; set; }
        public string notesContentTextBox { get; set; }
        public Note SelectedNote { get; private set; }
        public Medicament SelectedMed { get; private set; }
        public List<Medicament> patientMedsList { get; set; }
        public List<Note> patientStoryList { get; set; }

        public SubmitPatientViewModel()
        {
            this.goBackCommand = new RelayCommand(() => GoBackCommand());
            this.goToGenerateReceipt = new RelayCommand(() => GoToGenerateReceipt());
            this.openNoteWindowCommand = new RelayCommand(() => OpenNoteWindowCommand());
            this.showNoteCommand = new RelayCommandWithParameters((parameter) => ShowNoteCommand(parameter));
            this.showMedCommand = new RelayCommandWithParameters((parameter) => ShowMedCommand(parameter));
            this.saveNoteCommand = new RelayCommand(() => SaveNoteCommand());
            var patient = IoC.Get<ApplicationWindowViewModel>().Patient;
            if(patient == null)
            {
                return;
            }
            this.patientName = $"{patient.first_name} {patient.last_name}";
            this.patientAgeLabel = patient.age.ToString();
            this.patientMail = patient.mail;
            this.patientSexLabel = patient.sex;
            this.patientStoryList = GetStoryFromPatient(IoC.Get<ApplicationWindowViewModel>().Patient);
            this.patientMedsList = GetMedsFromPatient(IoC.Get<ApplicationWindowViewModel>().Patient);
        }

        

        private List<Medicament> GetMedsFromPatient(User patient)
        {
            List<Medicament> medsList = new List<Medicament>();

            DatabaseRetriever databaseRetriever = new DatabaseRetriever(IoC.Get<ApplicationWindowViewModel>().databaseManager);
            var patientStoryData = databaseRetriever.GetFromDatabase(
                tableName: "leki",
                columns: new List<string> { "nazwa"},
                where: $"JOIN leki.pacjent_leki " +
                $"ON leki.pacjent_leki.leki_id = leki.leki.id " +
                $"JOIN leki.pacjent " +
                $"ON leki.pacjent.id = leki.pacjent_leki.pacjent_id " +
                $"WHERE leki.pacjent.id = {IoC.Get<ApplicationWindowViewModel>().Patient.id}"
                );


            foreach (string medsName in patientStoryData)
            {
                medsList.Add(new Medicament(medsName));
            }

            return medsList;
        }
        private void ShowMedCommand(object parameter)
        {
            Medicament openedMed = parameter as Medicament;
            openedMed.GetRestOfMedsData();
            this.SelectedMed = openedMed;
            this.medicationWindow?.Close();
            this.medicationWindow = new MedicationWindow(this);
            this.medicationWindow?.Show();
        }
        private void ShowNoteCommand(object parameter)
        {
            Note openedNote = parameter as Note;
            this.SelectedNote = openedNote;
            this.showNoteWindow?.Close();
            this.showNoteWindow = new ShowNoteWindow(this);
            this.showNoteWindow?.Show();
        }

        public List<Note> GetStoryFromPatient(User patient)
        {
            List<Note> storyList = new List<Note>();

            DatabaseRetriever databaseRetriever = new DatabaseRetriever(IoC.Get<ApplicationWindowViewModel>().databaseManager);
            var patientStoryData = databaseRetriever.GetFromDatabase(
                tableName:"notatki",
                columns:new List<string> { "tytul", "zawartosc","imie","nazwisko", "data_stworzenia" },
                where: $"JOIN leki.lekarz ON leki.lekarz.id=leki.notatki.autor_id WHERE leki.notatki.pacjent_id={IoC.Get<ApplicationWindowViewModel>().Patient.id}",
                rowsToGet:100
                );
            int howManyNotes = patientStoryData.Count / 5;
            for (int i = 0; i < howManyNotes; i++)
            {
                var title = patientStoryData[0] ?? "<Not found>";
                var content = patientStoryData[1] ?? "<Not found>";
                var author = $"{patientStoryData[2]} {patientStoryData[3]}" ?? "<Not found>";
                var date = DateTime.Parse(patientStoryData[4]);
                storyList.Add(new Note(title, content, author, date));
                patientStoryData.RemoveRange(0, 5);
            }

            return storyList;
        }

        private void SaveNoteCommand()
        {
            DatabaseEditor databaseEditor = new DatabaseEditor(IoC.Get<ApplicationWindowViewModel>().databaseManager);
            string title = this.notesTitleTextBox;
            string content = this.notesContentTextBox;
            int patient_id = IoC.Get<ApplicationWindowViewModel>().Patient.id;
            int doctor_id = IoC.Get<ApplicationWindowViewModel>().Doctor.id;
            databaseEditor.InsertIntoDatabase(
                "notatki",
                new List<string> { "tytul", "zawartosc", "pacjent_id", "autor_id" },
                new List<string> { title, content},
                new List<int> { patient_id, doctor_id}
                );

            this.patientStoryList = GetStoryFromPatient(IoC.Get<ApplicationWindowViewModel>().Patient);
            noteWindow.Close();
        }

        private void OpenNoteWindowCommand()
        {
            this.noteWindow?.Close();
            this.noteWindow = new AddNoteWindow(this);
            this.noteWindow?.Show();
        }

        private void GoToGenerateReceipt()
        {
            IoC.Get<ApplicationWindowViewModel>().currentPage = ApplicationPage.GenerateReceipt;
        }

        private void GoBackCommand()
        {
            IoC.Get<ApplicationWindowViewModel>().currentPage = ApplicationPage.Patient;
        }
    }
}
