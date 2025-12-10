/**
 * Data structure representing an overdue report.
 * Stores a list of overdue items and the total fine value.
 */
package library.service;

	import java.util.List;
	import libraryy.MediaItem;

	public class OverdueReport {

	    private List<MediaItem> items;
	    private int totalFine;

	    public OverdueReport(List<MediaItem> items, int totalFine) {
	        this.items = items;
	        this.totalFine = totalFine;
	    }

	    public int getTotalFine() {
	        return totalFine;
	    }

	    public List<MediaItem> getItems() {
	        return items;
	    }
	}

