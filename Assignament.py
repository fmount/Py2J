def assignamentFunction():
	dict = {0:"rosso", 1:"verde", 2:"blu"}
	tup = (0,1,2)
	u = 8
	for x in range(len(tup)):
		if dict[x]=="rosso":
			print "rosso"
			list = ["rosa", "fucsia", "rosso fuoco"]
			i = len(list)
			while i<=0:
				print list[i]
				print dict[1]
				print tup[1:3]
				i = i-1
		else:
			print "Sara' un altro colore..."
			altra_tup = (9,8,7,6,tup)
			altro_dic = {"pippo":u , "cio":8}
			list2 = ["h","e","y","a"]
			for single_elem in list2:
				print single_elem

print "Prova Assignament Function"
assignamentFunction()

tu = (1,2,"ciao","tupla")
for hd in tu:
	print hd

v = 2
while v<=0:
	print v


