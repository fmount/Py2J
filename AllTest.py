def AreaCerchio(val):
	area = 3.14 * val * 2
	return area

def func1():
	x = "Hello World"
	print x

def func2():
	#ma x non è definita dentro func2
	u = x + " e Francesca!"
	print u



var1 = var2 = "stringa"
if var2=="stringa":
	var1 = 10

x1 =  (1,2,3,4)
x2 =   [1, "str1", 2 , "str2"]
x3 = { 1: "str1" , 2: "str2" }
y = x2

x = "stringa" + " string2"

h = 22
x = 12 + 13 - h
x =  10 / 77.25 * 100

X = (1,2,3)
y2 = X + (4,5,6)

X = ["string1", "string2"]
y3 = X * 2
print y3

t = ( 1, 2, 3)
x5, y5, z5 = t

t = (1,2,3,4,5,6,7,8,9)
X = t[1]
X = t[:2]
X = t[2:4]
X = t[-2]
X = t[1:-3]
print X

rubrica = {"Federica":34718187, "Azzurra":34822122}
rubrica["Francesca"] = 33474347
numero = rubrica["Federica"]
print numero

lista = ["cane","gatto","leone","serpente"]
for singElem in lista:
	print singElem


print "\nESEMPIO RANGE - 1:"
for Numero in range(20): 
	print Numero

print "\nESEMPIO RANGE - 2:"
for Numero in range(5, 10):  
	print Numero

print "\nESEMPIO RANGE - 3:"
for Numero in range(1, 10, 2):  
	print Numero

print "\nESEMPIO RANGE + LEN:"
lista = ["cane","gatto","leone","serpente"]
for i in range( len(lista) ):
	print lista[i] , " posizione indice: ", i

print "\nESEMPIO IF - 1:"
a = 10
b = "string"
if a>5 and b=="string":
	print "condizione IF verificata!"

# ESEMPIO IF - 2 (ERRORE VOLONTARIO):
c = 22.77
if c!="string":
	print "ok"

print "\nESEMPIO IF/ELSE - 3:"
x = 12%2
if x==0:
	print x, " e' pari"
else:
	print x, " e' dispari"

print "\nESEMPIO IF/ELSE/ELIF - 3:"
x = 18
y = 22
if x < y: 
	print x, " e' minore di ", y 
elif x > y: 
	print x, " e' maggiore di ", y 
else: 
	print x, " e ", y, " sono uguali"

print "\nESEMPIO WHILE - 1:"
n = 5
x = 18
while n > 0 or x <= 22: 
	print n 
	n = n-1
	x = x+7
	print x

try:
	x = 18 / 0
except ZeroDivisionError:
	print "Errore! Stai cercando di effettura una divisione per ZERO!"

area = AreaCerchio(2)
print "Area cerchio di raggio 2: ", area
