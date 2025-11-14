import os
import struct
import sys
import math

def writeResTxt(fName, result):
    """Запис результату у текстовий файл"""
    with open(fName, 'w') as f:
        f.write(str(result))

def readResTxt(fName):
    """Читання результату з текстового файлу"""
    result = 0.0
    try:
        if os.path.exists(fName):
            with open(fName, 'r') as f:
                result = f.read()
        else:
            raise FileNotFoundError(f"File {fName} not found.")
    except FileNotFoundError as e:
        print(e)
    return result

def writeResBin(fName, result):
    """Запис результату у двійковий файл (float64, 8 байтів)"""
    with open(fName, 'wb') as f:
        f.write(struct.pack('d', result))  # 'd' = double (float64)

def readResBin(fName):
    """Читання результату з двійкового файлу (float64, 8 байтів)"""
    result = 0.0
    try:
        if os.path.exists(fName):
            with open(fName, 'rb') as f:
                result = struct.unpack('d', f.read())[0]
        else:
            raise FileNotFoundError(f"File {fName} not found.")
    except FileNotFoundError as e:
        print(e)
    return result

def calculate(x):
    """
    Обчислення виразу:
    y = sin(3x - 5) / ctg(2x) = sin(3x - 5) * tan(2x)
    """
    return math.sin(3 * x - 5) * math.tan(2 * x)

if __name__ == "__main__":
    data = float(input("Enter x: "))
    result = calculate(data)
    result = round(result, 3)
    print(f"Result is: {result}")

    try:
        writeResTxt("textRes.txt", result)
        writeResBin("binRes.bin", result)

        print("Result from BIN file:", readResBin("binRes.bin"))
        print("Result from TXT file:", readResTxt("textRes.txt"))
    except FileNotFoundError as e:
        print(e)
        sys.exit(1)
