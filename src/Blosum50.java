public class Blosum50 {

	private static final float[][] scores =
	/* A R N D C Q E G H I L K M F P S T W Y V */
	{ /* A */
			{ 5 },
			/* R */{ -2, 7 },
			/* N */{ -1, -1, 7 },
			/* D */{ -2, -2, 2, 8 },
			/* C */{ -1, -4, -2, -4, 13 },
			/* Q */{ -1, 1, 0, 0, -3, 7 },
			/* E */{ -1, 0, 0, 2, -3, 2, 6 },
			/* G */{ 0, -3, 0, -1, -3, -2, -3, 8 },
			/* H */{ -2, 0, 1, -1, -3, 1, 0, -2, 10 },
			/* I */{ -1, -4, -3, -4, -2, -3, -4, -4, -4, 5 },
			/* L */{ -2, -3, -4, -4, -2, -2, -3, -4, -3, 2, 5 },
			/* K */{ -1, 3, 0, -1, -3, 2, 1, -2, 0, -3, -3, 6 },
			/* M */{ -1, -2, -2, -4, -2, 0, -2, -3, -1, 2, 3, -2, 7 },
			/* F */{ -3, -3, -4, -5, -2, -4, -3, -4, -1, 0, 1, -4, 0, 8 },
			/* P */{ -1, -3, -2, -1, -4, -1, -1, -2, -2, -3, -4, -1, -3, -4, 10 },
			/* S */{ 1, -1, 1, 0, -1, 0, -1, 0, -1, -3, -3, 0, -2, -3, -1, 5 },
			/* T */{ 0, -1, 0, -1, -1, -1, -1, -2, -2, -1, -1, -1, -1, -2, -1,
					2, 5 },
			/* W */{ -3, -3, -4, -5, -5, -1, -3, -3, -3, -3, -2, -3, -1, 1, -4,
					-4, -3, 15 },
			/* Y */{ -2, -1, -2, -3, -3, -1, -2, -3, 2, -1, -1, -2, 0, 4, -3,
					-2, -2, 2, 8 },
			/* V */{ 0, -3, -3, -4, -1, -3, -3, -4, -4, 4, 1, -3, 1, -1, -3,
					-2, 0, -3, -1, 5 },
			/* - */{ -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5,
					-5, -5, -5, -5, -5, -5, 1 }
	/* A R N D C Q E G H I L K M F P S T W Y V - */
	};

	public static float getScore(char c, char d) {
		char[] tabChar = new char[] { c, d };
		int[] tabInt = new int[2];
		for (int i = 0; i < 2; i++) {
			switch (tabChar[i]) {
			case 'A':
				tabInt[i] = 0;
				break;
			case 'R':
				tabInt[i] = 1;
				break;
			case 'N':
				tabInt[i] = 2;
				break;
			case 'D':
				tabInt[i] = 3;
				break;
			case 'C':
				tabInt[i] = 4;
				break;
			case 'Q':
				tabInt[i] = 5;
				break;
			case 'E':
				tabInt[i] = 6;
				break;
			case 'G':
				tabInt[i] = 7;
				break;
			case 'H':
				tabInt[i] = 8;
				break;
			case 'I':
				tabInt[i] = 9;
				break;
			case 'L':
				tabInt[i] = 10;
				break;
			case 'K':
				tabInt[i] = 11;
				break;
			case 'M':
				tabInt[i] = 12;
				break;
			case 'F':
				tabInt[i] = 13;
				break;
			case 'P':
				tabInt[i] = 14;
				break;
			case 'S':
				tabInt[i] = 15;
				break;
			case 'T':
				tabInt[i] = 16;
				break;
			case 'W':
				tabInt[i] = 17;
				break;
			case 'Y':
				tabInt[i] = 18;
				break;
			case 'V':
				tabInt[i] = 19;
				break;
			case '-':
				tabInt[i] = 20;
				break;
			default:
				System.out.println("Wrong parameters to getScore");
				return Integer.MIN_VALUE;
			}
		}
		// int tmp;
		if (tabInt[0] >= tabInt[1])
			return scores[tabInt[0]][tabInt[1]];
		else
			return scores[tabInt[1]][tabInt[0]];

	}

}
