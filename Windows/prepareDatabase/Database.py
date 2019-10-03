import mysql.connector
import csv
import re


def main():
    try:
        mydb = mysql.connector.connect(
            host="127.0.0.1",
            user="admin",
            password="admin",
            database='leki',
        )

        mycursor = mydb.cursor()

        with open('UpdatedData.csv', 'r') as file:
            csv_reader = csv.reader(file)
            for i, row in enumerate(csv_reader):
                if i == 0:
                    continue
                row = parseValues(row)
                commandToInsertValues(row[0], row[1],row[2], row[3], row[4], row[5])

        mydb.close()
    except Exception as e:
        print(e)


def parseValues(row: list):
    parsedRow = []
    nazwa = re.split(r'\s+- zastosowanie*', row[0])[0]
    parsedRow.append(nazwa)
    parsedRow.append(row[1])
    parsedRow.append(row[2])
    parsedRow.append(row[3])
    parsedRow.append(row[4])
    parsedRow.append(row[5])

    return parsedRow


def commandToInsertValues(nazwa, sklad, postac, kategoria, specjalnosc, efekt, info="", dawkowanie=""):
    command = "Insert Into leki VALUES ({0} ,{1} ,{2} ,{3} ,{4} ,{5} ,{6} ,{7})".format(nazwa, sklad, postac, kategoria
                                                                                        , specjalnosc, efekt, info,
                                                                                        dawkowanie)
    print(command)
    return command


def test():
    try:
        mydb = mysql.connector.connect(
            host="127.0.0.1",
            user="admin",
            password="admin",
            database='leki',
        )

        mycursor = mydb.cursor()

        mycursor.execute("SELECT * FROM leki")

        myresult = mycursor.fetchall()

        for x in myresult:
            print(x)
    except Exception as e:
        print(e)


if __name__ == '__main__':
    # main()
    test()
