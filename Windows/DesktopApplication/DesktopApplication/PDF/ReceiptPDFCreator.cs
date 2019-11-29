using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows;
using SautinSoft.Document;
using SautinSoft.Document.Drawing;
using SautinSoft.Document.Tables;

namespace DesktopApplication
{
    public class ReceiptPDFCreator
    {
        private DateTime dateOfControl = DateTime.Now.AddMonths(1);
        public string comments = "";
        private List<Medicament> medsList;
        public User patient;
        public User doctor; 
        private Image qrImage = null;
        private bool isGenerateQRCode  = false;
        private Image barCodeImage = null;
        private string barCodeTestValue = "TESTTESTTESTTESTTESTTESTTESTTEST";
        private string barCodeFilePath = "barcode.png";
        private string qrCodeFilePath = "qrcode.png";
        private string medsCode = "";

        public ReceiptPDFCreator(List<Medicament> medsList, string comments, Image qrImage)
        {
            Random rnd = new Random();
            for (int i = 0; i < 4; i++)
            {
                medsCode += rnd.Next(0, 9).ToString();
            }
            if (qrImage != null)
            {
                this.qrImage = qrImage;
                this.isGenerateQRCode = true;
            }
            this.medsList = medsList;
            this.patient = IoC.Get<ApplicationWindowViewModel>().Patient;
            this.doctor = IoC.Get<ApplicationWindowViewModel>().Doctor;
            this.comments = comments ?? "";
            Zen.Barcode.Code128BarcodeDraw barCode = Zen.Barcode.BarcodeDrawFactory.Code128WithChecksum;

            barCodeImage = barCode.Draw(barCodeTestValue, 50);
            barCodeImage.Save(barCodeFilePath);
        }

        public ReceiptPDFCreator()
        {
            Zen.Barcode.Code128BarcodeDraw barCode = Zen.Barcode.BarcodeDrawFactory.Code128WithChecksum;

            barCodeImage = barCode.Draw(barCodeTestValue, 50);
            barCodeImage.Save(barCodeFilePath);

            Zen.Barcode.CodeQrBarcodeDraw qrCode = Zen.Barcode.BarcodeDrawFactory.CodeQr;
            qrImage = qrCode.Draw("Test", 70);
            qrImage.Save(qrCodeFilePath);
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
            table.TableFormat.Alignment = SautinSoft.Document.HorizontalAlignment.Center;

            // Add rows.
            AddZeroRow(dc, table, width, 80);
            AddFirstRow(dc, table, width, 100);
            for (int i = 0; i < medsList.Count; i++)
            {
                AddSecondRow(dc, table, width, 100, medsList[i], i+1);
            }
            if(isGenerateQRCode)
            {
                AddThirdRow(dc, table, width, 80);
            }

            // Add the table into the section.
            s.Blocks.Add(table);

            // Save our document into PDF format.
            dc.Save(pdfSavePath, new PdfSaveOptions() { Compliance = PdfCompliance.PDF_A });

            // Open the result for demonstration purposes.
            try
            {
                System.Diagnostics.Process.Start(new System.Diagnostics.ProcessStartInfo(pdfSavePath) { UseShellExecute = true });
            }
            catch (Exception e)
            {
                MessageBox.Show(e.ToString());
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
            p.ParagraphFormat.Alignment = SautinSoft.Document.HorizontalAlignment.Center;
            p.Content.Start.Insert("Informacja o receptach elektronicznych", new CharacterFormat() 
            { FontName = "Arial", Size = 10.0 , Bold=true, UnderlineStyle=UnderlineType.Single});

            Paragraph p3 = new Paragraph(dc);
            p3.ParagraphFormat.Alignment = SautinSoft.Document.HorizontalAlignment.Left;
            p3.Content.Start.Insert("Recepta ogólna", new CharacterFormat()
            { FontName = "Arial", Size = 10.0, Bold = true, UnderlineStyle = UnderlineType.Single });

            //BARCODE:
            Paragraph p2 = new Paragraph(dc);
            p2.ParagraphFormat.Alignment = SautinSoft.Document.HorizontalAlignment.Center;
            Picture picture = new Picture(dc, this.barCodeFilePath);
            p2.Inlines.Add(picture);

            cell.Blocks.Add(p);
            cell.Blocks.Add(p3);
            cell.Blocks.Add(p2);

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
            p.ParagraphFormat.Alignment = SautinSoft.Document.HorizontalAlignment.Left;

            p.Content.Start.Insert(   "Kod dostepowy:        ", new CharacterFormat() { FontName = "Arial", Size = 10.0, Bold = true });
            p.Inlines.Add(new Run(dc,this.medsCode));
            p.Inlines.Add(new SpecialCharacter(dc, SpecialCharacterType.LineBreak));
            p.Inlines.Add(new Run(dc, "Pacjent:                      ", new CharacterFormat() { FontName = "Arial", Size = 10.0, Bold = true }));
            p.Inlines.Add(new Run(dc,$"{this.patient.first_name} {this.patient.last_name}"));
            p.Inlines.Add(new SpecialCharacter(dc, SpecialCharacterType.LineBreak));
            p.Inlines.Add(new Run(dc, "Wystawiono:              ", new CharacterFormat() { FontName = "Arial", Size = 10.0, Bold = true }));
            p.Inlines.Add(new Run(dc, DateTime.Now.ToString("MM/dd/yyyy H:mm")));
            p.Inlines.Add(new SpecialCharacter(dc, SpecialCharacterType.LineBreak));
            p.Inlines.Add(new Run(dc, "Wystawca:                  ", new CharacterFormat() { FontName = "Arial", Size = 10.0, Bold = true }));
            p.Inlines.Add(new Run(dc, $"lek. {this.doctor.first_name} {this.doctor.last_name}"));
            p.Inlines.Add(new SpecialCharacter(dc, SpecialCharacterType.LineBreak));
            p.Inlines.Add(new Run(dc, "Termin kontroli:         ", new CharacterFormat() { FontName = "Arial", Size = 10.0, Bold = true }));
            p.Inlines.Add(new Run(dc,dateOfControl.ToString("MM/dd/yyyy H:mm")));

            cell.Blocks.Add(p);

            table.Rows.Add(row);
        }
        private void AddSecondRow(DocumentCore dc, Table table, double width, double height, Medicament meds, int counter)
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
            p.ParagraphFormat.Alignment = SautinSoft.Document.HorizontalAlignment.Left;
            p.Content.Start.Insert($"Recepta {counter} z {this.medsList.Count} ogółem", new CharacterFormat()
            { FontName = "Arial", Size = 10.0, Bold = true, UnderlineStyle = UnderlineType.Single });

            //BARCODE:
            Paragraph p2 = new Paragraph(dc);
            p2.ParagraphFormat.Alignment = SautinSoft.Document.HorizontalAlignment.Center;
            Picture picture = new Picture(dc, this.barCodeFilePath);
            p2.Inlines.Add(picture);

            Paragraph p3 = new Paragraph(dc);
            p3.ParagraphFormat.Alignment = SautinSoft.Document.HorizontalAlignment.Left;
            p3.Content.Start.Insert("Przepisano:       ", new CharacterFormat()
            { FontName = "Arial", Size = 10.0, Bold = true });
            p3.Inlines.Add(new Run(dc, $"{meds.Name} {meds.dosageWeight} mg"));
            p3.Inlines.Add(new SpecialCharacter(dc, SpecialCharacterType.LineBreak));
            p3.Inlines.Add(new Run(dc, $"                              {meds.howManyPacks} op. po {meds.howManyPills} tabl."));
            p3.Inlines.Add(new SpecialCharacter(dc, SpecialCharacterType.LineBreak));
            p3.Inlines.Add(new Run(dc, $"                              D.S {meds.howManyPerDay}"));
            p3.Inlines.Add(new SpecialCharacter(dc, SpecialCharacterType.LineBreak));

            cell.Blocks.Add(p);
            cell.Blocks.Add(p2);
            cell.Blocks.Add(p3);

            table.Rows.Add(row);
        }
        private void AddThirdRow(DocumentCore dc, Table table, double width, double height)
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
            p.ParagraphFormat.Alignment = SautinSoft.Document.HorizontalAlignment.Center;
            p.Content.Start.Insert($"Recepta ogólna przeznaczona dla aplikacji MedBase", new CharacterFormat()
            { FontName = "Arial", Size = 10.0, Bold = true, UnderlineStyle = UnderlineType.Single });

            //QRCODE:
            Paragraph p2 = new Paragraph(dc);
            p2.ParagraphFormat.Alignment = SautinSoft.Document.HorizontalAlignment.Center;
            Picture picture = new Picture(dc, this.qrCodeFilePath);
            p2.Inlines.Add(picture);

            Paragraph p3 = new Paragraph(dc);
            p3.ParagraphFormat.Alignment = SautinSoft.Document.HorizontalAlignment.Left;
            p3.Content.Start.Insert($"Dodatkowe uwagi:", new CharacterFormat()
            { FontName = "Arial", Size = 10.0, Bold = true, UnderlineStyle = UnderlineType.Single });
            p3.Inlines.Add(new SpecialCharacter(dc, SpecialCharacterType.LineBreak));
            p3.Inlines.Add(new Run(dc, this.comments));

            cell.Blocks.Add(p);
            cell.Blocks.Add(p2);
            cell.Blocks.Add(p3);

            table.Rows.Add(row);
        }
    }
}
