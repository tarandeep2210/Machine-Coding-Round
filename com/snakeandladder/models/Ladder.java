package com.snakeandladder.models;

public class Ladder {

		private int end;
		private int start;
		
		public Ladder(int start,int end){
			this.setStart(start);
			this.setEnd(end);
		}

		public int getEnd() {
			return end;
		}

		public void setEnd(int end) {
			this.end = end;
		}

		public int getStart() {
			return start;
		}

		public void setStart(int start) {
			this.start = start;
		}
	
}
