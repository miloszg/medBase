using System;
using System.Collections.Generic;
using System.Windows.Input;

namespace DesktopApplication
{
    public class MedsViewModel : BaseViewModel
    {

        private DatabaseManager databaseManager;
        private DatabaseRetriever databaseRetriever;

        public ICommand goBackCommand { get; set; }
        public ICommand searchCommand { get; set; }

        public List<string> categoryItems { get; set; }
        public List<string> formItems { get; set; }
        public List<string> specialityItems { get; set; }
        public List<Medicament> foundMedsList { get; set; }

        public string selectedCategory { get; set; }
        public string selectedSpeciality { get; set; }
        public string selectedForm { get; set; }

        public MedsViewModel()
        {
            this.goBackCommand = new RelayCommand(() => GoBackCommand());
            this.searchCommand = new RelayCommand(() => SearchCommand());
            databaseManager = new DatabaseManager("localhost", "leki", "admin", "admin");
            databaseRetriever = new DatabaseRetriever(databaseManager);
            categoryItems = databaseRetriever.GetFromDatabase("kategoria", new List<string>
            {
                "nazwa"
            });
            formItems = databaseRetriever.GetFromDatabase("postac", new List<string>
            {
                "nazwa"
            });
            specialityItems= databaseRetriever.GetFromDatabase("specjalnosc", new List<string>
            {
                "nazwa"
            });
            categoryItems.Insert(0, $"<pusty>");
            formItems.Insert(0, "<pusty>");
            specialityItems.Insert(0, "<pusty>");
        }
        public void GoBackCommand()
        {
            IoC.Get<ApplicationWindowViewModel>().currentPage = ApplicationPage.MainMenu;
        }

        private void SearchCommand()
        {
            List<string> selectedFilters = new List<string>();

            string whereCommand = PrepareWhereCommand(selectedFilters);

            var foundedMeds = databaseRetriever.GetFromDatabaseManyToMany("leki",
                new List<string> { "DISTINCT leki.nazwa" },
                new List<string> { "kategoria", "postac", "specjalnosc" },
                whereCommand
            );

            foundMedsList = CreateFoundMedsList(foundedMeds);
            if(foundMedsList.Count == 0)
            {
                foundMedsList.Add(new Medicament("Nie znaleziono wynikow"));
            }
        }

        private string PrepareWhereCommand(List<string> selectedFilters)
        {
            if (selectedCategory != null && selectedCategory != "<pusty>")
            {
                selectedFilters.Add($"kategoria.nazwa = \'{selectedCategory}\'");
            }
            if (selectedForm != null && selectedForm != "<pusty>")
            {
                selectedFilters.Add($"postac.nazwa = \'{selectedForm}\'");
            }
            if (selectedSpeciality != null && selectedSpeciality != "<pusty>")
            {
                selectedFilters.Add($"specjalnosc.nazwa = \'{selectedSpeciality}\'");
            }

            string whereCommand = $"WHERE";
            for (int i = 0; i < selectedFilters.Count; i++)
            {
                if (i == 0)
                {
                    whereCommand += $" {selectedFilters[i]}";
                }
                else
                {
                    whereCommand += $" AND {selectedFilters[i]}";
                }
            }
            if (whereCommand == "WHERE")
            {
                whereCommand = "";
            }

            return whereCommand;
        }

        private List<Medicament> CreateFoundMedsList(List<string> foundedMeds)
        {
            List<Medicament> foundMedsList = new List<Medicament>();

            foreach (string medsName in foundedMeds)
            { 
                foundMedsList.Add(new Medicament(medsName));
            }

            return foundMedsList;
        }

    }
}
