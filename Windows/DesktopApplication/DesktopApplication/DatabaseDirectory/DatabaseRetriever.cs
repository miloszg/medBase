using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace DesktopApplication
{
    public class DatabaseRetriever
    {
        private readonly DatabaseManager databaseManager;

        public DatabaseRetriever(DatabaseManager databaseManager)
        {
            this.databaseManager = databaseManager;
        }

        public List<string> GetFromDatabase(string tableName, List<string> columns = null, string where = "",int rowsToGet = -1)
        {
            List<string> dataFromDatabase = new List<string>();

            if (databaseManager.OpenConnection() == false)
            {
                return dataFromDatabase;
            }

            if(rowsToGet == -1)
            {
                var cmd = new MySqlCommand($"SELECT COUNT(*) FROM leki.{tableName}", this.databaseManager.GetConnection());
                rowsToGet = Convert.ToInt32(cmd.ExecuteScalar());
            }

            string columnsOfTable = "*";
            if (columns != null)
            {
                columnsOfTable = this.databaseManager.MySqlListToStringConverter(columns, SqlCommandsEnum.ColumnsOrTables);
            }

            string command = $"SELECT {columnsOfTable} FROM leki.{tableName} {where};";

            MySqlDataReader dataReader;
            try
            {
                MySqlCommand sqlCommand = new MySqlCommand(command, this.databaseManager.GetConnection());
                dataReader = sqlCommand.ExecuteReader();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                MessageBox.Show(ex.ToString(), "ERROR!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return dataFromDatabase;
            }

            
            dataFromDatabase = databaseManager.GetRowsFromTable(dataReader,rowsToGet);

            return dataFromDatabase;
        }
        public List<string> GetFromDatabaseManyToMany(string tableName, List<string> columns, List<string> joinTable, string where = "", int rowsToGet = 1000)
        {
            List<string> dataFromDatabase = new List<string>();

            if (databaseManager.OpenConnection() == false)
            {
                return dataFromDatabase;
            }

            string columnsOfTable = "*";
            if (columns != null)
            {
                columnsOfTable = this.databaseManager.MySqlListToStringConverter(columns, SqlCommandsEnum.ColumnsOrTables);
            }

            string command = CreateManyToManyCommand(tableName, joinTable, where, columnsOfTable);

            MySqlDataReader dataReader;
            try
            {
                MySqlCommand sqlCommand = new MySqlCommand(command, this.databaseManager.GetConnection());
                dataReader = sqlCommand.ExecuteReader();
            }
            catch (MySqlException ex)
            {
                MessageBox.Show(ex.ToString(), "ERROR!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return dataFromDatabase;
            }


            dataFromDatabase = databaseManager.GetRowsFromTable(dataReader, rowsToGet);

            return dataFromDatabase;
        }

        private string CreateManyToManyCommand(string tableName, List<string> joinTable, string where, string columnsOfTable)
        {
            string command = $"SELECT {columnsOfTable} FROM leki.{tableName} ";
            foreach (string table in joinTable)
            {
                command += $"JOIN leki.{tableName}_{table} ON leki.{tableName}.id = leki.{tableName}_{table}.{tableName}_id " +
                    $"JOIN leki.{table} ON leki.{tableName}_{table}.{table}_id = leki.{table}.id ";
            }
            command += $"{where};";
            return command;
        }
    }
}
