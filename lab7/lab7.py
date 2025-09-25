def generate_jagged_list(n: int, symbol: str):

    jagged_list = []
    for i in range(n):
        row = [symbol] * (n - i)
        jagged_list.append(row)
    return jagged_list


def print_jagged_list(jagged_list):
    for i, row in enumerate(jagged_list):
        print(" " * i + "".join(row))

def main():
    try:
        n = int(input("Введіть розмір матриці: "))
    except ValueError:
        print("Потрібно ввести ціле число!")
        return

    symbol = input("Введіть символ-заповнювач: ")

    if len(symbol) != 1:
        print("Потрібно ввести лише один символ!")
        return

    jagged_list = generate_jagged_list(n, symbol)

    print("\nЗгенерований масив:\n")
    print_jagged_list(jagged_list)


if __name__ == "__main__":
    main()
