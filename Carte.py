class Carta:
	Seme = 0
	Rango = 0
	ListaSemi = ["Fiori","Quadri","Cuori","Picche"]
	ListaRanghi = ["Impossibile","Asso","2","3","4","5","6","7","8","9","10","Jack","Regina","Re"]
	def __init__(self,Seme,Rango):
		self.Seme = Seme
		self.Rango = Rango
	def getCarta(self):
		y = self.ListaRanghi[self.Rango]
		x = self.ListaSemi[self.Seme]
		return y+" di "+x
	def confrontaCarte(self,altraCarta_rango, altraCarta_seme):
		#Supponiamo che il seme ha priorità rispetto al rango
		# controllo il seme
		if self.Seme > altraCarta_seme:
			return 1
		if self.Seme < altraCarta_seme:
			return -1
		# se i semi sono uguali controlla il rango
		if self.Rango > altraCarta_rango:
			return 1
		if self.Rango < altraCarta_rango:
			return -1
		# se anche i ranghi sono uguali le carte sono uguali!
		return 0

class Mazzo:
	Carte = []
	def __init__(self):
		for Seme in range(4):
			for Rango in range(1, 14):
				oneCarta = Carta(Seme, Rango)
				list = [oneCarta]
				self.Carte = self.Carte + list
	def getCarta(self,index):
		singleElem = Carte[index]
		return singleElem
	def stampaMazzo(self):
		for x in range(52):
			oneCarta = getCarta(x)
			print "\n^^^-- CARTA NUMERO ",x ," --^^^"
			print "RANGO: ", oneCarta.Rango
			print "SEME: ", oneCarta.Seme
			print "\n"


TreDiFiori = Carta(0,3)
print "Carta: ", TreDiFiori.getCarta()
carta2 = Carta(0,11)
print "Carta: ", carta2.getCarta()
print "Confronto Carte: "
x = TreDiFiori.confrontaCarte(carta2.Rango,carta2.Seme)
print x
m = Mazzo()
m.stampaMazzo()
