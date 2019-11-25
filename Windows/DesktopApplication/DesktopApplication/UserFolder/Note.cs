using System;

namespace DesktopApplication
{
    public class Note
    {
        public string Title { get; set; }
        public string Content { get; set; }
        public string Author { get; set; }
        public string Date { get; set; }
        public Note(string title, string content, string author, DateTime date)
        {
            this.Title = title;
            this.Content = content;
            this.Author = author;
            if (date.ToString("dd-MM-yyyy") == "01.01.0001")
            {
                this.Date = "<Not Found>";
            }
            else
            {
                this.Date = date.ToString("dd-MM-yyyy");
            }
        }

    } 
}
