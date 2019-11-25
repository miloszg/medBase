using System.Collections.Generic;

namespace DesktopApplication
{
    public class Medicament
    {
        public string Name { get; private set; }
        public List<string> Effect { get; private set; }
        public List<string> Category { get; private set; }
        public List<string> Form { get; private set; }
        public List<string> Speciality { get; private set; }
        public List<string> Ingredients { get; private set; }
        public string Description { get; private set; }
        public string howMany { get; set; }
        public string howToTake { get; set; }
        public string howLongToTake { get; set; }
        public bool IsThisAllData { get; private set; }
        public string ImagePath { get; private set; }

        public Medicament(string name)
        {
            this.Name = name;
            this.Effect = new List<string>();
            this.Category = new List<string>();
            this.Form = new List<string>();
            this.Speciality = new List<string>();
            this.Ingredients = new List<string>();
            this.Description = "<pusty>";
            this.ImagePath = @"Resources\Images\NoImageAvailable.jpg";
            this.IsThisAllData = false;
        }

        public void GetRestOfMedsData()
        {
            if(this.IsThisAllData)
            {
                return;
            }
            DatabaseRetriever databaseRetriever = new DatabaseRetriever(IoC.Get<ApplicationWindowViewModel>().databaseManager);
            var data = databaseRetriever.GetFromDatabase(
                tableName:"leki",
                columns:new List<string> {"s.nazwa", "k.nazwa", "sp.nazwa", "e.nazwa", "p.nazwa"},
                where: "JOIN leki.leki_skladniki ls " +
                "ON leki.leki.id = ls.leki_id " +
                "JOIN leki.skladniki s " +
                "ON s.id = ls.skladniki_id " +
                "JOIN leki.leki_kategoria lk " +
                "ON leki.leki.id = lk.leki_id " +
                "JOIN leki.kategoria k " +
                "ON k.id = lk.kategoria_id " +
                "JOIN leki.leki_specjalnosc lsp " +
                "ON leki.leki.id = lsp.leki_id " +
                "JOIN leki.specjalnosc sp " +
                "ON sp.id = lsp.specjalnosc_id " +
                "JOIN leki.leki_efekt le " +
                "ON leki.leki.id = le.leki_id " +
                "JOIN leki.efekt e " +
                "ON e.id = le.efekt_id " +
                "JOIN leki.leki_postac lp " +
                "ON leki.leki.id = lp.leki_id " +
                "JOIN leki.postac p " +
                "ON p.id = lp.postac_id " +
                $"WHERE leki.leki.nazwa = \"{this.Name}\""
                );

            int howManyColumns = 5;
            int howManyMeds = data.Count / howManyColumns;
            for (int i = 0; i < howManyMeds; i++)
            {
                if (!this.Ingredients.Contains(data[0]))
                {
                    this.Ingredients.Add(data[0]);
                }
                if (!this.Category.Contains(data[1]))
                {
                    this.Category.Add(data[1]);
                }
                if (!this.Speciality.Contains(data[2]))
                {
                    this.Speciality.Add(data[2]);
                }
                if (!this.Effect.Contains(data[3]))
                {
                    this.Effect.Add(data[3]);
                }
                if (!this.Form.Contains(data[4]))
                {
                    this.Form.Add(data[4]);
                }

                data.RemoveRange(0, howManyColumns);
            }

            this.IsThisAllData = true;
        }
    }
}
