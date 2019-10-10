using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace DesktopApplication
{
    public class DatabaseRetriever
    {
        private readonly IDatabaseManager databaseConnector;

        public DatabaseRetriever(IDatabaseManager _databaseConnector)
        {
            databaseConnector = _databaseConnector;
        }

        public List<string> GetFromDatabase(string tableName, List<string> columns = null, string where = "")
        {
            List<string> dataFromDatabase = new List<string>();

            if (databaseConnector.OpenConnection() == false)
            {
                return dataFromDatabase;
            }

            string columnsOfTable = "*";
            if (columns != null)
            {
                columnsOfTable = this.databaseConnector.ParametersListToString(columns, true);
            }

            string command = $"SELECT {columnsOfTable} FROM leki.{tableName} {where};";

            MySqlDataReader dataReader;
            try
            {
                MySqlCommand sqlCommand = new MySqlCommand(command, this.databaseConnector.GetConnection());
                dataReader = sqlCommand.ExecuteReader();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                MessageBox.Show(ex.ToString(), "ERROR!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return dataFromDatabase;
            }

            dataFromDatabase = databaseConnector.GetRowsFromTable(dataReader);

            return dataFromDatabase;
        }
    }
}
