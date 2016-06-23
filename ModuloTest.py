from __future__ import division
import random
import unittest

class ModuloTest(unittest.TestCase):

	def test_negmod(self):
		for _ in xrange(10000):
			a = random.randint(-10000, 10000)
			b = random.randint(-10000, 10000)
			if b == 0:
				b = random.randint(1, 10000)
			q = a // b
			r = a % b
			self.assertEqual(a, q * b + r)

if __name__ == '__main__':
	unittest.main()
