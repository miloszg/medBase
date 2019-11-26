using System;
using System.Collections.Generic;
using System.Drawing;
using SautinSoft.Document;
using SautinSoft.Document.Drawing;
using SautinSoft.Document.Tables;

namespace DesktopApplication
{
    public class ReceiptPDFCreator
    {
        public string comments = "";
        private List<Medicament> medsList;
        public User patient;
        public User doctor;

        private Image qrImage = null;
        private bool isGenerateQRCode  = false;

        public ReceiptPDFCreator(List<Medicament> medsList, string comments, Image qrImage)
        {
            if(qrImage != null)
            {
                this.qrImage = qrImage;
                this.isGenerateQRCode = true;
            }
            this.medsList = medsList;
            this.patient = IoC.Get<ApplicationWindowViewModel>().Patient;
            this.doctor = IoC.Get<ApplicationWindowViewModel>().Doctor;
            this.comments = comments ?? "";
        }

        public ReceiptPDFCreator()
        {

        }

        public void GeneratePDF(string pdfSavePath)
        {
            // Let's create a new document.
            DocumentCore dc = new DocumentCore();

            // Add a new section.
            Section s = new Section(dc);
            dc.Sections.Add(s);

            // Let's create a plain table: 2x3, 100 mm of width.
            Table table = new Table(dc);
            double width = LengthUnitConverter.Convert(100, LengthUnit.Millimeter, LengthUnit.Point);
            table.TableFormat.PreferredWidth = new TableWidth(width, TableWidthUnit.Point);
            table.TableFormat.Alignment = HorizontalAlignment.Center;

            // Add rows.
            AddZeroRow(dc, table, width,80);
            AddFirstRow(dc, table, width,100);
            AddSecondRow(dc, table, width,150);
            AddThirdRow(dc, table, width,100);

           
            // Add the table into the section.
            s.Blocks.Add(table);

            // Save our document into PDF format.
            dc.Save(pdfSavePath, new PdfSaveOptions() { Compliance = PdfCompliance.PDF_A });

            // Open the result for demonstration purposes.
            try
            {
                System.Diagnostics.Process.Start(new System.Diagnostics.ProcessStartInfo(pdfSavePath) { UseShellExecute = true });
            }
            catch (Exception)
            {

            }
            
        }

        private void AddZeroRow(DocumentCore dc, Table table, double width, double height)
        {
            TableRow row = new TableRow(dc);

            TableCell cell = new TableCell(dc);

            row.RowFormat.Height = new TableRowHeight(height, HeightRule.AtLeast);

            // First Row.
            cell.CellFormat.Borders.SetBorders(MultipleBorderTypes.Outside, BorderStyle.Single, SautinSoft.Document.Color.Black, 1.0);

            // Set the same width for each column.
            cell.CellFormat.PreferredWidth = new TableWidth(width, TableWidthUnit.Point);

            row.Cells.Add(cell);

            // Let's add a paragraph with text into the each column.
            Paragraph p = new Paragraph(dc);
            p.ParagraphFormat.Alignment = HorizontalAlignment.Left;
            p.Content.Start.Insert("Recepta", new CharacterFormat() { FontName = "Arial", Size = 10.0 });

            //ENTER:
            p.Inlines.Add(new SpecialCharacter(dc, SpecialCharacterType.LineBreak));

            string tekst = $"{this.doctor.first_name} {this.doctor.last_name}";
            Run text1 = new Run(dc, tekst);
            p.Inlines.Add(text1);
            p.Inlines.Add(new SpecialCharacter(dc, SpecialCharacterType.LineBreak));

            cell.Blocks.Add(p);
            table.Rows.Add(row);
        }
        private void AddFirstRow(DocumentCore dc, Table table, double width, double height)
        {
            TableRow row = new TableRow(dc);

            TableCell cell = new TableCell(dc);

            row.RowFormat.Height = new TableRowHeight(height, HeightRule.AtLeast);

            // First Row.
            cell.CellFormat.Borders.SetBorders(MultipleBorderTypes.Outside, BorderStyle.Single, SautinSoft.Document.Color.Black, 1.0);

            // Set the same width for each column.
            cell.CellFormat.PreferredWidth = new TableWidth(width, TableWidthUnit.Point);

            row.Cells.Add(cell);

            // Let's add a paragraph with text into the each column.
            Paragraph p = new Paragraph(dc);
            p.ParagraphFormat.Alignment = HorizontalAlignment.Left;

            p.Content.Start.Insert("Pacjent", new CharacterFormat() { FontName = "Arial", Size = 10.0 });

            //ENTER:
            p.Inlines.Add(new SpecialCharacter(dc, SpecialCharacterType.LineBreak));
            string tekst = $"{this.patient.first_name} {this.patient.last_name}";
            Run text1 = new Run(dc, tekst);
            p.Inlines.Add(text1);
            p.Inlines.Add(new SpecialCharacter(dc, SpecialCharacterType.LineBreak));

            cell.Blocks.Add(p);

            table.Rows.Add(row);
        }
        private void AddSecondRow(DocumentCore dc, Table table, double width, double height)
        {
            TableRow row = new TableRow(dc);

            TableCell cell = new TableCell(dc);

            row.RowFormat.Height = new TableRowHeight(height, HeightRule.AtLeast);

            // First Row.
            cell.CellFormat.Borders.SetBorders(MultipleBorderTypes.Outside, BorderStyle.Single, SautinSoft.Document.Color.Black, 1.0);

            // Set the same width for each column.
            cell.CellFormat.PreferredWidth = new TableWidth(width, TableWidthUnit.Point);

            row.Cells.Add(cell);

            // Let's add a paragraph with text into the each column.
            Paragraph p = new Paragraph(dc);
            p.ParagraphFormat.Alignment = HorizontalAlignment.Left;
            p.Content.Start.Insert("Rp", new CharacterFormat() { FontName = "Arial", Size = 10.0 });
            p.Inlines.Add(new SpecialCharacter(dc, SpecialCharacterType.LineBreak));

            Run text1;

            foreach (var meds in this.medsList)
            {
                string tekst = $"Nazwa:{meds.Name}, Ilosc opakowan: {meds.howMany}, Dawkowanie: {meds.howToTake}, Czas przyjmowania: {meds.howLongToTake}";
                text1 = new Run(dc, tekst);
                p.Inlines.Add(text1);
                p.Inlines.Add(new SpecialCharacter(dc, SpecialCharacterType.LineBreak));
            }

            p.Inlines.Add(new SpecialCharacter(dc, SpecialCharacterType.LineBreak));
            p.Inlines.Add(new Run(dc,"Uwagi:"));
            p.Inlines.Add(new SpecialCharacter(dc, SpecialCharacterType.LineBreak));
            text1 = new Run(dc, this.comments);
            p.Inlines.Add(text1);

            cell.Blocks.Add(p);

            table.Rows.Add(row);
        }
        private void AddThirdRow(DocumentCore dc, Table table, double width, double height)
        {
            TableRow row = new TableRow(dc);

            TableCell cell = new TableCell(dc);

            row.RowFormat.Height = new TableRowHeight((3.0/5.0)*height, HeightRule.AtLeast);

            // First Row.
            cell.CellFormat.Borders.SetBorders(MultipleBorderTypes.Outside, BorderStyle.Single, SautinSoft.Document.Color.Black, 1.0);

            // Set the same width for each column.
            cell.CellFormat.PreferredWidth = new TableWidth(width, TableWidthUnit.Point);

            row.Cells.Add(cell);

            // Let's add a paragraph with text into the each column.
            Paragraph p = new Paragraph(dc);
            p.ParagraphFormat.Alignment = HorizontalAlignment.Left;

            p.Content.Start.Insert("Data wystawienia:", new CharacterFormat() { FontName = "Arial", Size = 10.0 });

            p.Inlines.Add(new SpecialCharacter(dc, SpecialCharacterType.LineBreak));
            string tekst = DateTime.Now.ToString("dd-MM-yyyy");
            Run text1 = new Run(dc, tekst);
            p.Inlines.Add(text1);
            p.Inlines.Add(new SpecialCharacter(dc, SpecialCharacterType.LineBreak));

            cell.Blocks.Add(p);

            table.Rows.Add(row);

            row = new TableRow(dc);
            cell = new TableCell(dc);

            row.RowFormat.Height = new TableRowHeight((2.0 / 5.0) * height, HeightRule.AtLeast);

            // First Row.
            cell.CellFormat.Borders.SetBorders(MultipleBorderTypes.Outside, BorderStyle.Single, SautinSoft.Document.Color.Black, 1.0);

            // Set the same width for each column.
            cell.CellFormat.PreferredWidth = new TableWidth(20, TableWidthUnit.Point);

            row.Cells.Add(cell);

            // Let's add a paragraph with text into the each column.
            p = new Paragraph(dc);
            p.ParagraphFormat.Alignment = HorizontalAlignment.Left;

            p.Content.Start.Insert("Data realizacji \"od dnia\":", new CharacterFormat() { FontName = "Arial", Size = 10.0 });

            p.Inlines.Add(new SpecialCharacter(dc, SpecialCharacterType.LineBreak));
            text1 = new Run(dc, tekst);
            p.Inlines.Add(text1);
            p.Inlines.Add(new SpecialCharacter(dc, SpecialCharacterType.LineBreak));

            if(isGenerateQRCode)
            {
                Picture picture = new Picture(dc, "qrcode.png");
                p.Inlines.Add(picture);
            }

            cell.Blocks.Add(p);
            table.Rows.Add(row);
        }
    }
}
