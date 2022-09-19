package views.UI;

import java.util.ArrayList;
import java.util.List;

public class UiMenu {
	private String title;
	private List<String> options = new ArrayList<>();

	public UiMenu() {}
	public UiMenu(String title, List<String> opetions) {
		this.setTitle(title.toUpperCase());
		this.addOptions(opetions);
	}
	
	private String getTitle() {
		return title;
	}
	private void setTitle(String title) {
		this.title = title;
	}
	private List<String> getOptions() {
		return options;
	}
	
	private void addOptions(List<String> options) {
		for(String option: options) {
			this.options.add(option);
		}
	}
	
	public void  showMenu() {
		System.out.printf("===================================\n");
		System.out.printf("%s\n\n", this.getTitle());
		System.out.println("Entre com a opção:");
		Integer index = 1;
		for(String option: this.getOptions()) {
			System.out.printf("    %d - %s\n", index, option);
			index++;
		}
		System.out.print(" -> ");
	}
	
}
