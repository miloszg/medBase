import csv


def main():
    new_csv = []
    with open('data.csv', 'r') as csv_file:
        csv_reader = csv.reader(csv_file)
        for i, row in enumerate(csv_reader):
            if i % 2 == 0:
                new_csv.append(row)

    for row in new_csv:
        print(row)
    with open('UpdatedData.csv', 'w', newline='') as csv_file:
        csv_writer = csv.writer(csv_file, delimiter=',')
        for row in new_csv:
            csv_writer.writerow(row)


def test():
    pass


if __name__ == '__main__':
    main()
