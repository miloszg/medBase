using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data;
using System.Text;
using System.Windows.Forms;

namespace DesktopApplication
{
    public class DatabaseEditor
    {
        private readonly DatabaseManager databaseManager;

        public DatabaseEditor(DatabaseManager databaseManager)
        {
            this.databaseManager = databaseManager;
        }

        public bool InsertIntoDatabase(string tableName,List<string> columns, List<string> parameters)
        {
            if (!databaseManager.OpenConnection())
            {
                return false;
            }
            string columnsOfTable = this.databaseManager.MySqlListToStringConverter(columns,SqlCommandsEnum.ColumnsOrTables);
            string values = this.databaseManager.MySqlListToStringConverter(parameters);
            
            string command = $"INSERT INTO leki.{tableName} ({columnsOfTable}) VALUE ({values});";
            try
            {
                MySqlCommand sqlCommand = new MySqlCommand(command, this.databaseManager.GetConnection());
                MySqlDataReader dataReader = sqlCommand.ExecuteReader();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                MessageBox.Show(ex.ToString(),"ERROR!",MessageBoxButtons.OK, MessageBoxIcon.Error);
                return false;
            }

            return true;
        }
    }
}
