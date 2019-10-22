using MySql.Data.MySqlClient;
using System.Collections.Generic;
using System.Data;
using System.Text;
using System.Windows;

namespace DesktopApplication
{
    public class DatabaseManager
    {
        public MySqlConnection connection { get; set; }
        private string server;
        private string database;
        private string uid;
        private string password;

        public DatabaseManager(string server, string database, string uid, string password)
        {
            this.server = server;
            this.database = database;
            this.uid = uid;
            this.password = password;
        }

        public void Initialize()
        {
            string connectionString = $"SERVER={this.server}; DATABASE={this.database}; UID={this.uid}; PASSWORD={this.password}";
            this.connection = new MySqlConnection(connectionString);
        }

        public bool OpenConnection()
        {
            Initialize();

            try
            {
                this.connection?.Open();
                return true;
            }
            catch (MySqlException ex)
            {
                //When handling errors, you can your application's response based 
                //on the error number.
                //The two most common error numbers when connecting are as follows:
                //0: Cannot connect to server.
                //1045: Invalid user name and/or password.
                switch (ex.Number)
                {
                    case 0:
                        MessageBox.Show("Cannot connect to server.  Contact administrator");
                        break;

                    case 1045:
                        MessageBox.Show("Invalid username/password, please try again");
                        break;
                }
                return false;
            }
        }
        public MySqlConnection GetConnection()
        {
            return this.connection;
        }

        public string GetColumnsOfTable(string tableName)
        {
            if (this.OpenConnection() == false)
            {
                return string.Empty;
            }

            string command = $"SHOW COLUMNS FROM {tableName}";
            MySqlCommand sqlCommand = new MySqlCommand(command, this.GetConnection());
            MySqlDataReader dataReader = sqlCommand.ExecuteReader();

            StringBuilder columnString = new StringBuilder();

            var dt = dataReader.GetSchemaTable();
            foreach (DataRow col in dt.Rows)
            {
                columnString.Append(col.Field<string>("ColumnName") + ", ");
            }

            return columnString.ToString();
        }

        public List<string> GetRowsFromTable(MySqlDataReader dataReader, int rowsToGet=1000)
        {
            List<string> tableString = new List<string>();
            for (int row = 0; row < rowsToGet; row++)
            {
                if (!dataReader.Read())
                {
                    break;
                }
                if(!dataReader.HasRows)
                {
                    return tableString;
                }
                for (int columnNumber = 0; columnNumber < dataReader.FieldCount; columnNumber++)
                {
                    if (!dataReader.IsDBNull(columnNumber))
                    {
                        tableString.Add(dataReader.GetString(columnNumber));
                    }
                }
            }

            return tableString;
        }

        public string MySqlListToStringConverter(List<string> parameters, SqlCommandsEnum listType = SqlCommandsEnum.NormalValues)
        {
            StringBuilder parametersString = new StringBuilder();

            for (int i = 0; i < parameters.Count; i++)
            {
                switch (listType)
                {
                    case SqlCommandsEnum.NormalValues:
                        parametersString.Append($"\"{parameters[i]}\"");
                        break;
                    case SqlCommandsEnum.ColumnsOrTables:
                        parametersString.Append($"{parameters[i]}");
                        break;
                    case SqlCommandsEnum.ColunsWithTables:
                        parametersString.Append($"leki.{parameters[i]}");
                        break;
                    default:
                        break;
                }
                if (i < parameters.Count - 1)
                {
                    parametersString.Append(",");
                }
            }
            return parametersString.ToString();
        }
    }
}
