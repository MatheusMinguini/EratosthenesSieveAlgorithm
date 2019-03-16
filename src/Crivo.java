import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Crivo {
	
	private static Scanner scanner;
	private static Integer amountNumbersEnrased = 0;
	private static Integer lastIndexChecked = 0;

	public static void main(String[] args) {
		System.out.println("Digite até qual número deseja listar os primos: ");
		scanner = new Scanner(System.in);
		BigInteger typedNumber = scanner.nextBigInteger();
		
		List<Integer> listOfPrimes = findAllNumbersUpToGivenNumber(typedNumber);
		
		long start = System.currentTimeMillis();
		
		System.out.println("Processando...");
		do {
			amountNumbersEnrased = enraseMultiples(listOfPrimes);
		}while(amountNumbersEnrased != 0);
		
		long elapsed = System.currentTimeMillis() - start;
		
		printPrimes(listOfPrimes, elapsed);
	}

	private static int enraseMultiples(List<Integer> listOfPrimes) {

		amountNumbersEnrased = 0;
		
		for(int index = lastIndexChecked + 1; index < listOfPrimes.size(); index++) {
			if((listOfPrimes.get(index) % listOfPrimes.get(lastIndexChecked)) == 0) {
				listOfPrimes.remove(index);
				amountNumbersEnrased++;
			}
		}
		
		lastIndexChecked++;
		
		return amountNumbersEnrased;
		
	}

	private static void printPrimes(List<Integer> listOfPrimes, long elapsed) {
		StringBuilder factors = new StringBuilder("Fatores primos encontrados:\n");
		
		int counter = 0;
		
		for(int index = 0; index < listOfPrimes.size(); index++) {
			if(index == listOfPrimes.size() - 1) {
				factors.append(listOfPrimes.get(index));
			}else{
				factors.append(listOfPrimes.get(index) + ", ");
			}
			
			if(counter == 20) {
				factors.append("\n");
				counter = 0;
			}
			
			counter++;
		}
		
		System.out.println(factors);
		System.out.println("\n\n***************** RESULTADO *****************");
		System.out.println("Tempo de processamento: ");
		System.out.println("Hora(s): ");
		System.out.println("Minuto(s): " + (elapsed / 6000));
		System.out.println("Segundo(s): " + (elapsed / 1000));
		System.out.println("Total de primos encontrados: " + listOfPrimes.size());
	}
	
	private static List<Integer> findAllNumbersUpToGivenNumber(BigInteger limit) {
		List<Integer> allNumbers = new ArrayList<Integer>();
		
		Integer index = 2;
		
		while(BigInteger.valueOf(index).compareTo(limit) <= 0) {
			allNumbers.add(index);
			index++;
		}
		
		return allNumbers;
	}

}
