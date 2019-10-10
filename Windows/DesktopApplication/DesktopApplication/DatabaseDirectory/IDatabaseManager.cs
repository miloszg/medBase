using MySql.Data.MySqlClient;
using System.Collections.Generic;

namespace DesktopApplication
{
    public interface IDatabaseManager
    {
        MySqlConnection GetConnection();
        void Initialize();
        bool OpenConnection();
        string GetColumnsOfTable(string tableName);
        List<string> GetRowsFromTable(MySqlDataReader dataReader, int rowsToGet = 1);
        string ParametersListToString(System.Collections.Generic.List<string> parameters, bool columns=false);
    }
}