package org.genadidharma.jjjyuk.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DestinationResponse{

	@SerializedName("destinations")
	private List<Destination> destinations;

	public List<Destination> getDestinations(){
		return destinations;
	}
}