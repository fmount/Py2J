class A():
	var = 10
	tupla = ("1",2,"43",5,6)
	def __init__(self,var):
		self.var = var
	def metodo1(self):
		print "Metodo 'metodo1' della classe A"
	def metodo2(self):
		print "Metodo 'metodo2' della classe A"


class B:
	var=20
	var_2 = "ciao"
	def metodo1(self):
		print "Metodo 'metodo1' della classe B"
	def metodo2(self):
		print "Metodo 'metodo2' della classe B"
	def metodo3(self):
		print "Metodo 'metodo3' della classe B"


class C(A,B):
	def __init__(self,a):
		A.__init__(a)
		print "costruttore con parametri"
	def metodo1(self):
		print "Metodo 'metodo1' della classe C"
		return "ok"
	def gestioneAttributi(self):
		if self.var==10:
			print "La variabile della classe C - deriva dalla classe A"
			num = self.var
			while num > 0:
				print num
				num = num - 1
		else:
			print "La variabile della classe C - deriva dalla classe B"
		if var_2=="pippo":
			print "La variabile appartiene alla classe B"
		else:
			print "La variabile appartiene alla classe C"


def ereditFunction():
	obj = C(10)
	obj.metodo1()
	obj.metodo2()
	obj.metodo3()
	obj.gestioneAttributi()
	print obj.var


print "\n\n"
ereditFunction()

