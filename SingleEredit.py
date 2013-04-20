class Impiegato:
	nome = ""
	salario = 0.0
	matricola = ""
	anniDiServizio = 0

	def __init__(self,n,salario,m,ads):
		self.nome = n
		self.salario = salario
		self.matricola = m
		self.anniDiServizio = ads

	def incrementaSalario(self, percentuale):
		tmp = 1.0 + percentuale/100
		self.salario = self.salario * tmp

	def stampaInfo(self):
		print self.nome," ",self.salario," ",self.matricola

	def getNome(self):
		return self.nome

	def getAnniServizio(self):
		return self.anniDiServizio

	def getSalario(self):
		return self.salario;


class Manager(Impiegato):
	nomeSegretaria = ""

	def __init__(self,n,s,m,ads,nameS):
		Impiegato.__init__(n,s,m,ads)
		self.nomeSegretaria = nameS

	def getNomeSegretaria(self):
		return self.nomeSegretaria

	def setNomeSegretaria(self, n):
		self.nomeSegretaria = n

	def modificaSalario(self, percento):
		# Aggiunge alla percentuale lo 0.5% per ogni anno di servizio
		bonus = getAnniServizio()
		bonus = bonus * 0.5
		newVal = bonus + percento
		incrementaSalario(newVal)

def par(g,t,y):
	print "ciao"


m1 = Manager("Ing Heather Rivera ", 250000, "O55/000043", 6, "Signora Lopez")
m2 = Manager("Ing Carmelo Verdi", 66000, "O55/237723", 12, "Signora Biachi")
i = Impiegato("Dott Paolo Rossi", 22000, "O55/1234567", 35)


print "Prima dell' incremento del salario: ", m1.getSalario()
percento = 0.5
m1.modificaSalario(percento)
m1.setNomeSegretaria("marco")
print "\n*** DATI - Ing. Heather Rivera ***\n"
m1.stampaInfo()
print "\n\n"

anniSRossi = i.getAnniServizio()
print "Anni di servizio del Dott. Rossi: ",i.getAnniServizio()
anniSVerdi = m2.getAnniServizio()
print "Anni di servizio del Dott. Verdi: ", m2.getAnniServizio()

if anniSVerdi >= anniSRossi:
	print "L'Ing. Verdi ha più anni di servizio del Dott. Rossi"
else:
	print "Il Dott. Rossi ha più anni di servizio dell'Ing. Verdi"

par(1,2,3)
