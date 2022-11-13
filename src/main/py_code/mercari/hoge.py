# -*- coding: utf-8 -*-
import mercari
import sys
import io

sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8')

with open("test.txt", mode='w',encoding='utf-8') as f:
    for item in mercari.search("ミランクンデラ"):
        print(item.productName,item.price)
        f.write(item.productName)