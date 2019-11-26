from bs4 import BeautifulSoup
from urllib.request import urlopen
import csv


class Drug:

    def __init__(self, _name, _composition, _form, _category, _speciality, _effect, _info="", _dosage=""):
        self.name = _name
        self.composition = _composition
        self.form = _form
        self.category = _category
        self.speciality = _speciality
        self.effect = _effect
        self.info = _info
        self.dosage = _dosage

    def __str__(self):
        return "{},{},{},{},{},{},{},{}".format(self.name, self.composition, self.form, self.category,
                                                self.speciality, self.effect, self.info, self.dosage)


def main():
    header = ["Name", "Composition", "Form", "Category", "Speciality", "Effect", "Info", "Dosage"]
    with open("data.csv", 'w') as file:
        writer = csv.writer(file)
        writer.writerow(header)
    file.close()

    for i in range(2, 207, 10):
        url_address = "https://www.doz.pl/leki/w_{}-wszystkie".format(i)
        print(url_address)
        html = getHtml(url_address)
        webscrapp_data_to_file(html)

    print("Zapis zakonczony sukcesem!")


def getHtml(url_address):
    return urlopen(url_address)


def create_drug_list(drug_url_list):
    return [get_drug_info(url) for url in drug_url_list]


def webscrapp_data_to_file(html):
    drugs_url_list = get_drugs_url(html)

    drug_list = create_drug_list(drugs_url_list)

    try:
        with open("data.csv", 'a', newline='') as file:
            writer = csv.writer(file)
            for drug in drug_list:
                writer.writerow(drug)

        file.close()
    except IOError as e:
        print("Couldn't open or write to file (%s)." % e)


def get_drugs_url(html):
    soup = BeautifulSoup(html, 'html.parser')
    en_items = soup.findAll("div", {"class": "encyclopediaItem col-lg-12 col-sm-12"}, limit=10)
    en_items_list = [("https://www.doz.pl" + str(el.find("a")['href'])) for el in en_items]
    return en_items_list


def get_drug_info(url):
    soup = BeautifulSoup(getHtml(url), 'html.parser')
    _name = ""
    _composition = ""
    _form = ""
    _category = ""
    _speciality = ""
    _effect = ""
    tr = soup.findAll("tr")
    try:
        _name = soup.findAll('header')[1].find('h1').contents[0].strip()
        _composition = [a.contents[0] for a in tr[1].findAll('a') if len(a.contents) > 0]
        _form = [str(t).strip() for i, t in enumerate(tr[2].findAll('td')[1].contents) if i % 2 == 0]
        _category = [a.contents[0] for a in tr[3].findAll('a') if len(a.contents) > 0]
        _speciality = [a.contents[0] for a in tr[4].findAll('a') if len(a.contents) > 0]
        _effect = [a.contents[0] for a in tr[5].findAll('a') if len(a.contents) > 0]
    except IndexError as e:
        print(str(e))

    return [_name, _composition, _form, _category, _speciality, _effect]


def test():
    print("Hello world")


if __name__ == '__main__':
    main()
    # test()
