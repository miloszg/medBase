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
        private readonly IDatabaseManager databaseConnector;

        public DatabaseEditor(IDatabaseManager _databaseConnector)
        {
            this.databaseConnector = _databaseConnector;
        }

        public bool InsertIntoDatabase(string tableName,List<string> columns, List<string> parameters)
        {
            if (!databaseConnector.OpenConnection())
            {
                return false;
            }
            string columnsOfTable = this.databaseConnector.ParametersListToString(columns,true);
            string values = this.databaseConnector.ParametersListToString(parameters);
            
            string command = $"INSERT INTO leki.{tableName} ({columnsOfTable}) VALUE ({values});";
            try
            {
                MySqlCommand sqlCommand = new MySqlCommand(command, this.databaseConnector.GetConnection());
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
