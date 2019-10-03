import mysql.connector
import csv
import unidecode


def main():
    try:
        mydb = mysql.connector.connect(
            host="127.0.0.1",
            user="admin",
            password="admin",
            database='leki',
        )

        mycursor = mydb.cursor()

        # Fill Composition, Form, Category, Speciality, Effect
        composition = []
        form = []
        category = []
        speciality = []
        Effect = []
        meds = []

        with open('UpdatedData.csv', 'r') as file:
            csv_reader = csv.reader(file)
            for i, row in enumerate(csv_reader):
                if i == 0:
                    continue
                for el in row[1].strip().split(','):
                    composition.append(unidecode.unidecode(el.lstrip().strip()))
                for el in row[2].strip().split(','):
                    form.append(unidecode.unidecode(el.lstrip().strip()))
                for el in row[3].strip().split(','):
                    category.append(unidecode.unidecode(el.lstrip().strip()))
                for el in row[4].strip().split(','):
                    speciality.append(unidecode.unidecode(el.lstrip().strip()))
                for el in row[5].strip().split(','):
                    Effect.append(unidecode.unidecode(el.lstrip().strip()))

                meds.append(unidecode.unidecode(row[0].lstrip().strip()))

            composition = list(set(composition))
            form = list(set(form))
            category = list(set(category))
            speciality = list(set(speciality))
            Effect = list(set(Effect))

            """Sprawdz czy nie ma zadnego pustego obiektu"""
            for el in composition:
                if el == "":
                    composition.remove(el)
            for el in form:
                if el == "":
                    form.remove(el)
            for el in category:
                if el == "":
                    category.remove(el)
            for el in speciality:
                if el == "":
                    speciality.remove(el)
            for el in Effect:
                if el == "":
                    Effect.remove(el)

        for index, element in enumerate(composition):
            command = "INSERT INTO leki.skladniki (id,nazwa) VALUES ({0},\"{1}\");".format(index+1, element)
            mycursor.execute(command)
        for index, element in enumerate(form):
            command = "INSERT INTO leki.postac (id,nazwa) VALUES ({0},\"{1}\");".format(index+1, element)
            mycursor.execute(command)
        for index, element in enumerate(category):
            command = "INSERT INTO leki.kategoria (id, nazwa) VALUES ({0},\"{1}\");".format(index+1, element)
            mycursor.execute(command)
        for index, element in enumerate(speciality):
            command = "INSERT INTO leki.specjalnosc (id, nazwa) VALUES ({0},\"{1}\");".format(index+1, element)
            mycursor.execute(command)
        for index, element in enumerate(Effect):
            command = "INSERT INTO leki.efekt (id, nazwa) VALUES ({0},\"{1}\");".format(index+1, element)
            mycursor.execute(command)
        mydb.commit()

        for index, element in enumerate(meds):
            command = "INSERT INTO leki.leki (id,nazwa) VALUES ({0},\"{1}\");".format(index+1, element)
            mycursor.execute(command)

        mydb.commit()
        print("Zcommitowano obiekty")

        """Create Dependencies"""

        meds_comp = []
        meds_form = []
        meds_category = []
        meds_specs = []
        meds_effect = []
        with open('UpdatedData.csv', 'r') as file:
            csv_reader = csv.reader(file)
            for i, row in enumerate(csv_reader):
                if i == 0:
                    continue
                for el in row[1].strip().split(','):
                    if el == "":
                        continue
                    index = composition.index(unidecode.unidecode(el.lstrip().strip()))
                    meds_comp.append((i, index+1))
                for el in row[2].strip().split(','):
                    if el == "":
                        continue
                    index = form.index(unidecode.unidecode(el.lstrip().strip()))
                    meds_form.append((i, index + 1))
                for el in row[3].strip().split(','):
                    if el == "":
                        continue
                    index = category.index(unidecode.unidecode(el.lstrip().strip()))
                    meds_category.append((i, index + 1))
                for el in row[4].strip().split(','):
                    if el == "":
                        continue
                    index = speciality.index(unidecode.unidecode(el.lstrip().strip()))
                    meds_specs.append((i, index + 1))
                for el in row[5].strip().split(','):
                    if el == "":
                        continue
                    index = Effect.index(unidecode.unidecode(el.lstrip().strip()))
                    meds_effect.append((i, index + 1))

        for element in meds_comp:
            command = "INSERT INTO leki.leki_skladniki (leki_id,skladniki_id) VALUES ({0},{1});".format(element[0],element[1])
            mycursor.execute(command)
        for element in meds_form:
            command = "INSERT INTO leki.leki_postac (leki_id,postac_id) VALUES ({0},{1});".format(element[0],element[1])
            mycursor.execute(command)
        for element in meds_category:
            command = "INSERT INTO leki.leki_kategoria (leki_id,kategoria_id) VALUES ({0},{1});".format(element[0],element[1])
            mycursor.execute(command)
        for element in meds_specs:
            command = "INSERT INTO leki.leki_specjalnosc (leki_id,specjalnosc_id) VALUES ({0},{1});".format(element[0],element[1])
            mycursor.execute(command)
        for element in meds_effect:
            command = "INSERT INTO leki.leki_efekt (leki_id,efekt_id) VALUES ({0},{1});".format(element[0],element[1])
            mycursor.execute(command)


        mydb.commit()
        print("Zcommitowane zaleznosci")
        mydb.close()

    except Exception as e:
        print(e)

        mydb.close()


if __name__ == '__main__':
    main()
    # test()
