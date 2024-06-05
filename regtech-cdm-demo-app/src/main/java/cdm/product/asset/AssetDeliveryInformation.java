package cdm.product.asset;

import cdm.base.math.Quantity;
import cdm.base.staticdata.identifier.LocationIdentifier;
import cdm.product.asset.AssetDeliveryInformation;
import cdm.product.asset.AssetDeliveryInformation.AssetDeliveryInformationBuilder;
import cdm.product.asset.AssetDeliveryInformation.AssetDeliveryInformationBuilderImpl;
import cdm.product.asset.AssetDeliveryInformation.AssetDeliveryInformationImpl;
import cdm.product.asset.AssetDeliveryPeriods;
import cdm.product.asset.meta.AssetDeliveryInformationMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Contains the information relative to the delivery of the asset.
 * @version ${project.version}
 */
@RosettaDataType(value="AssetDeliveryInformation", builder=AssetDeliveryInformation.AssetDeliveryInformationBuilderImpl.class, version="${project.version}")
public interface AssetDeliveryInformation extends RosettaModelObject {

	AssetDeliveryInformationMeta metaData = new AssetDeliveryInformationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Defines the periods of delivery, including the delivery profile.
	 */
	AssetDeliveryPeriods getPeriods();
	/**
	 * Defines the location of the delivery of the commodity.
	 */
	List<? extends LocationIdentifier> getLocation();
	/**
	 * The number of units included in the transaction for each delivery interval
	 */
	Quantity getDeliveryCapacity();

	/*********************** Build Methods  ***********************/
	AssetDeliveryInformation build();
	
	AssetDeliveryInformation.AssetDeliveryInformationBuilder toBuilder();
	
	static AssetDeliveryInformation.AssetDeliveryInformationBuilder builder() {
		return new AssetDeliveryInformation.AssetDeliveryInformationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AssetDeliveryInformation> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AssetDeliveryInformation> getType() {
		return AssetDeliveryInformation.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("periods"), processor, AssetDeliveryPeriods.class, getPeriods());
		processRosetta(path.newSubPath("location"), processor, LocationIdentifier.class, getLocation());
		processRosetta(path.newSubPath("deliveryCapacity"), processor, Quantity.class, getDeliveryCapacity());
	}
	

	/*********************** Builder Interface  ***********************/
	interface AssetDeliveryInformationBuilder extends AssetDeliveryInformation, RosettaModelObjectBuilder {
		AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder getOrCreatePeriods();
		AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder getPeriods();
		LocationIdentifier.LocationIdentifierBuilder getOrCreateLocation(int _index);
		List<? extends LocationIdentifier.LocationIdentifierBuilder> getLocation();
		Quantity.QuantityBuilder getOrCreateDeliveryCapacity();
		Quantity.QuantityBuilder getDeliveryCapacity();
		AssetDeliveryInformation.AssetDeliveryInformationBuilder setPeriods(AssetDeliveryPeriods periods);
		AssetDeliveryInformation.AssetDeliveryInformationBuilder addLocation(LocationIdentifier location0);
		AssetDeliveryInformation.AssetDeliveryInformationBuilder addLocation(LocationIdentifier location1, int _idx);
		AssetDeliveryInformation.AssetDeliveryInformationBuilder addLocation(List<? extends LocationIdentifier> location2);
		AssetDeliveryInformation.AssetDeliveryInformationBuilder setLocation(List<? extends LocationIdentifier> location3);
		AssetDeliveryInformation.AssetDeliveryInformationBuilder setDeliveryCapacity(Quantity deliveryCapacity);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("periods"), processor, AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder.class, getPeriods());
			processRosetta(path.newSubPath("location"), processor, LocationIdentifier.LocationIdentifierBuilder.class, getLocation());
			processRosetta(path.newSubPath("deliveryCapacity"), processor, Quantity.QuantityBuilder.class, getDeliveryCapacity());
		}
		

		AssetDeliveryInformation.AssetDeliveryInformationBuilder prune();
	}

	/*********************** Immutable Implementation of AssetDeliveryInformation  ***********************/
	class AssetDeliveryInformationImpl implements AssetDeliveryInformation {
		private final AssetDeliveryPeriods periods;
		private final List<? extends LocationIdentifier> location;
		private final Quantity deliveryCapacity;
		
		protected AssetDeliveryInformationImpl(AssetDeliveryInformation.AssetDeliveryInformationBuilder builder) {
			this.periods = ofNullable(builder.getPeriods()).map(f->f.build()).orElse(null);
			this.location = ofNullable(builder.getLocation()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.deliveryCapacity = ofNullable(builder.getDeliveryCapacity()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("periods")
		public AssetDeliveryPeriods getPeriods() {
			return periods;
		}
		
		@Override
		@RosettaAttribute("location")
		public List<? extends LocationIdentifier> getLocation() {
			return location;
		}
		
		@Override
		@RosettaAttribute("deliveryCapacity")
		public Quantity getDeliveryCapacity() {
			return deliveryCapacity;
		}
		
		@Override
		public AssetDeliveryInformation build() {
			return this;
		}
		
		@Override
		public AssetDeliveryInformation.AssetDeliveryInformationBuilder toBuilder() {
			AssetDeliveryInformation.AssetDeliveryInformationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AssetDeliveryInformation.AssetDeliveryInformationBuilder builder) {
			ofNullable(getPeriods()).ifPresent(builder::setPeriods);
			ofNullable(getLocation()).ifPresent(builder::setLocation);
			ofNullable(getDeliveryCapacity()).ifPresent(builder::setDeliveryCapacity);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AssetDeliveryInformation _that = getType().cast(o);
		
			if (!Objects.equals(periods, _that.getPeriods())) return false;
			if (!ListEquals.listEquals(location, _that.getLocation())) return false;
			if (!Objects.equals(deliveryCapacity, _that.getDeliveryCapacity())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (periods != null ? periods.hashCode() : 0);
			_result = 31 * _result + (location != null ? location.hashCode() : 0);
			_result = 31 * _result + (deliveryCapacity != null ? deliveryCapacity.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AssetDeliveryInformation {" +
				"periods=" + this.periods + ", " +
				"location=" + this.location + ", " +
				"deliveryCapacity=" + this.deliveryCapacity +
			'}';
		}
	}

	/*********************** Builder Implementation of AssetDeliveryInformation  ***********************/
	class AssetDeliveryInformationBuilderImpl implements AssetDeliveryInformation.AssetDeliveryInformationBuilder {
	
		protected AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder periods;
		protected List<LocationIdentifier.LocationIdentifierBuilder> location = new ArrayList<>();
		protected Quantity.QuantityBuilder deliveryCapacity;
	
		public AssetDeliveryInformationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("periods")
		public AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder getPeriods() {
			return periods;
		}
		
		@Override
		public AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder getOrCreatePeriods() {
			AssetDeliveryPeriods.AssetDeliveryPeriodsBuilder result;
			if (periods!=null) {
				result = periods;
			}
			else {
				result = periods = AssetDeliveryPeriods.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("location")
		public List<? extends LocationIdentifier.LocationIdentifierBuilder> getLocation() {
			return location;
		}
		
		public LocationIdentifier.LocationIdentifierBuilder getOrCreateLocation(int _index) {
		
			if (location==null) {
				this.location = new ArrayList<>();
			}
			LocationIdentifier.LocationIdentifierBuilder result;
			return getIndex(location, _index, () -> {
						LocationIdentifier.LocationIdentifierBuilder newLocation = LocationIdentifier.builder();
						return newLocation;
					});
		}
		
		@Override
		@RosettaAttribute("deliveryCapacity")
		public Quantity.QuantityBuilder getDeliveryCapacity() {
			return deliveryCapacity;
		}
		
		@Override
		public Quantity.QuantityBuilder getOrCreateDeliveryCapacity() {
			Quantity.QuantityBuilder result;
			if (deliveryCapacity!=null) {
				result = deliveryCapacity;
			}
			else {
				result = deliveryCapacity = Quantity.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("periods")
		public AssetDeliveryInformation.AssetDeliveryInformationBuilder setPeriods(AssetDeliveryPeriods periods) {
			this.periods = periods==null?null:periods.toBuilder();
			return this;
		}
		@Override
		public AssetDeliveryInformation.AssetDeliveryInformationBuilder addLocation(LocationIdentifier location) {
			if (location!=null) this.location.add(location.toBuilder());
			return this;
		}
		
		@Override
		public AssetDeliveryInformation.AssetDeliveryInformationBuilder addLocation(LocationIdentifier location, int _idx) {
			getIndex(this.location, _idx, () -> location.toBuilder());
			return this;
		}
		@Override 
		public AssetDeliveryInformation.AssetDeliveryInformationBuilder addLocation(List<? extends LocationIdentifier> locations) {
			if (locations != null) {
				for (LocationIdentifier toAdd : locations) {
					this.location.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("location")
		public AssetDeliveryInformation.AssetDeliveryInformationBuilder setLocation(List<? extends LocationIdentifier> locations) {
			if (locations == null)  {
				this.location = new ArrayList<>();
			}
			else {
				this.location = locations.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("deliveryCapacity")
		public AssetDeliveryInformation.AssetDeliveryInformationBuilder setDeliveryCapacity(Quantity deliveryCapacity) {
			this.deliveryCapacity = deliveryCapacity==null?null:deliveryCapacity.toBuilder();
			return this;
		}
		
		@Override
		public AssetDeliveryInformation build() {
			return new AssetDeliveryInformation.AssetDeliveryInformationImpl(this);
		}
		
		@Override
		public AssetDeliveryInformation.AssetDeliveryInformationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AssetDeliveryInformation.AssetDeliveryInformationBuilder prune() {
			if (periods!=null && !periods.prune().hasData()) periods = null;
			location = location.stream().filter(b->b!=null).<LocationIdentifier.LocationIdentifierBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (deliveryCapacity!=null && !deliveryCapacity.prune().hasData()) deliveryCapacity = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPeriods()!=null && getPeriods().hasData()) return true;
			if (getLocation()!=null && getLocation().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getDeliveryCapacity()!=null && getDeliveryCapacity().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AssetDeliveryInformation.AssetDeliveryInformationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AssetDeliveryInformation.AssetDeliveryInformationBuilder o = (AssetDeliveryInformation.AssetDeliveryInformationBuilder) other;
			
			merger.mergeRosetta(getPeriods(), o.getPeriods(), this::setPeriods);
			merger.mergeRosetta(getLocation(), o.getLocation(), this::getOrCreateLocation);
			merger.mergeRosetta(getDeliveryCapacity(), o.getDeliveryCapacity(), this::setDeliveryCapacity);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AssetDeliveryInformation _that = getType().cast(o);
		
			if (!Objects.equals(periods, _that.getPeriods())) return false;
			if (!ListEquals.listEquals(location, _that.getLocation())) return false;
			if (!Objects.equals(deliveryCapacity, _that.getDeliveryCapacity())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (periods != null ? periods.hashCode() : 0);
			_result = 31 * _result + (location != null ? location.hashCode() : 0);
			_result = 31 * _result + (deliveryCapacity != null ? deliveryCapacity.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AssetDeliveryInformationBuilder {" +
				"periods=" + this.periods + ", " +
				"location=" + this.location + ", " +
				"deliveryCapacity=" + this.deliveryCapacity +
			'}';
		}
	}
}
