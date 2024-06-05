package cdm.product.asset;

import cdm.observable.asset.RateObservation;
import cdm.product.asset.FloatingRateDefinition;
import cdm.product.asset.FloatingRateDefinition.FloatingRateDefinitionBuilder;
import cdm.product.asset.FloatingRateDefinition.FloatingRateDefinitionBuilderImpl;
import cdm.product.asset.FloatingRateDefinition.FloatingRateDefinitionImpl;
import cdm.product.asset.meta.FloatingRateDefinitionMeta;
import cdm.product.template.Strike;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A data defining:  parameters associated with a floating rate reset. This data forms:  part of the cashflows representation of a stream.
 * @version ${project.version}
 */
@RosettaDataType(value="FloatingRateDefinition", builder=FloatingRateDefinition.FloatingRateDefinitionBuilderImpl.class, version="${project.version}")
public interface FloatingRateDefinition extends RosettaModelObject {

	FloatingRateDefinitionMeta metaData = new FloatingRateDefinitionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The final calculated rate for a calculation period after any required averaging of rates A calculated rate of 5% would be represented as 0.05.
	 */
	BigDecimal getCalculatedRate();
	/**
	 * The details of a particular rate observation, including the fixing date and observed rate. A list of rate observation elements may be ordered in the document by ascending adjusted fixing date. An FpML document containing an unordered list of rate observations is still regarded as a conformant document.
	 */
	List<? extends RateObservation> getRateObservation();
	/**
	 * A rate multiplier to apply to the floating rate. The multiplier can be a positive or negative decimal. This element should only be included if the multiplier is not equal to 1 (one).
	 */
	BigDecimal getFloatingRateMultiplier();
	/**
	 * The ISDA Spread, if any, which applies for the calculation period. The spread is a per annum rate, expressed as a decimal. For purposes of determining a calculation period amount, if positive the spread will be added to the floating rate and if negative the spread will be subtracted from the floating rate. A positive 10 basis point (0.1%) spread would be represented as 0.001.
	 */
	BigDecimal getSpread();
	/**
	 * The cap rate, if any, which applies to the floating rate for the calculation period. The cap rate (strike) is only required where the floating rate on a swap stream is capped at a certain strike level. The cap rate is assumed to be exclusive of any spread and is a per annum rate, expressed as a decimal. A cap rate of 5% would be represented as 0.05.
	 */
	List<? extends Strike> getCapRate();
	/**
	 * The floor rate, if any, which applies to the floating rate for the calculation period. The floor rate (strike) is only required where the floating rate on a swap stream is floored at a certain strike level. The floor rate is assumed to be exclusive of any spread and is a per annum rate, expressed as a decimal. The floor rate of 5% would be represented as 0.05.
	 */
	List<? extends Strike> getFloorRate();

	/*********************** Build Methods  ***********************/
	FloatingRateDefinition build();
	
	FloatingRateDefinition.FloatingRateDefinitionBuilder toBuilder();
	
	static FloatingRateDefinition.FloatingRateDefinitionBuilder builder() {
		return new FloatingRateDefinition.FloatingRateDefinitionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FloatingRateDefinition> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FloatingRateDefinition> getType() {
		return FloatingRateDefinition.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("calculatedRate"), BigDecimal.class, getCalculatedRate(), this);
		processRosetta(path.newSubPath("rateObservation"), processor, RateObservation.class, getRateObservation());
		processor.processBasic(path.newSubPath("floatingRateMultiplier"), BigDecimal.class, getFloatingRateMultiplier(), this);
		processor.processBasic(path.newSubPath("spread"), BigDecimal.class, getSpread(), this);
		processRosetta(path.newSubPath("capRate"), processor, Strike.class, getCapRate());
		processRosetta(path.newSubPath("floorRate"), processor, Strike.class, getFloorRate());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FloatingRateDefinitionBuilder extends FloatingRateDefinition, RosettaModelObjectBuilder {
		RateObservation.RateObservationBuilder getOrCreateRateObservation(int _index);
		List<? extends RateObservation.RateObservationBuilder> getRateObservation();
		Strike.StrikeBuilder getOrCreateCapRate(int _index);
		List<? extends Strike.StrikeBuilder> getCapRate();
		Strike.StrikeBuilder getOrCreateFloorRate(int _index);
		List<? extends Strike.StrikeBuilder> getFloorRate();
		FloatingRateDefinition.FloatingRateDefinitionBuilder setCalculatedRate(BigDecimal calculatedRate);
		FloatingRateDefinition.FloatingRateDefinitionBuilder addRateObservation(RateObservation rateObservation0);
		FloatingRateDefinition.FloatingRateDefinitionBuilder addRateObservation(RateObservation rateObservation1, int _idx);
		FloatingRateDefinition.FloatingRateDefinitionBuilder addRateObservation(List<? extends RateObservation> rateObservation2);
		FloatingRateDefinition.FloatingRateDefinitionBuilder setRateObservation(List<? extends RateObservation> rateObservation3);
		FloatingRateDefinition.FloatingRateDefinitionBuilder setFloatingRateMultiplier(BigDecimal floatingRateMultiplier);
		FloatingRateDefinition.FloatingRateDefinitionBuilder setSpread(BigDecimal spread);
		FloatingRateDefinition.FloatingRateDefinitionBuilder addCapRate(Strike capRate0);
		FloatingRateDefinition.FloatingRateDefinitionBuilder addCapRate(Strike capRate1, int _idx);
		FloatingRateDefinition.FloatingRateDefinitionBuilder addCapRate(List<? extends Strike> capRate2);
		FloatingRateDefinition.FloatingRateDefinitionBuilder setCapRate(List<? extends Strike> capRate3);
		FloatingRateDefinition.FloatingRateDefinitionBuilder addFloorRate(Strike floorRate0);
		FloatingRateDefinition.FloatingRateDefinitionBuilder addFloorRate(Strike floorRate1, int _idx);
		FloatingRateDefinition.FloatingRateDefinitionBuilder addFloorRate(List<? extends Strike> floorRate2);
		FloatingRateDefinition.FloatingRateDefinitionBuilder setFloorRate(List<? extends Strike> floorRate3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("calculatedRate"), BigDecimal.class, getCalculatedRate(), this);
			processRosetta(path.newSubPath("rateObservation"), processor, RateObservation.RateObservationBuilder.class, getRateObservation());
			processor.processBasic(path.newSubPath("floatingRateMultiplier"), BigDecimal.class, getFloatingRateMultiplier(), this);
			processor.processBasic(path.newSubPath("spread"), BigDecimal.class, getSpread(), this);
			processRosetta(path.newSubPath("capRate"), processor, Strike.StrikeBuilder.class, getCapRate());
			processRosetta(path.newSubPath("floorRate"), processor, Strike.StrikeBuilder.class, getFloorRate());
		}
		

		FloatingRateDefinition.FloatingRateDefinitionBuilder prune();
	}

	/*********************** Immutable Implementation of FloatingRateDefinition  ***********************/
	class FloatingRateDefinitionImpl implements FloatingRateDefinition {
		private final BigDecimal calculatedRate;
		private final List<? extends RateObservation> rateObservation;
		private final BigDecimal floatingRateMultiplier;
		private final BigDecimal spread;
		private final List<? extends Strike> capRate;
		private final List<? extends Strike> floorRate;
		
		protected FloatingRateDefinitionImpl(FloatingRateDefinition.FloatingRateDefinitionBuilder builder) {
			this.calculatedRate = builder.getCalculatedRate();
			this.rateObservation = ofNullable(builder.getRateObservation()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.floatingRateMultiplier = builder.getFloatingRateMultiplier();
			this.spread = builder.getSpread();
			this.capRate = ofNullable(builder.getCapRate()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.floorRate = ofNullable(builder.getFloorRate()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("calculatedRate")
		public BigDecimal getCalculatedRate() {
			return calculatedRate;
		}
		
		@Override
		@RosettaAttribute("rateObservation")
		public List<? extends RateObservation> getRateObservation() {
			return rateObservation;
		}
		
		@Override
		@RosettaAttribute("floatingRateMultiplier")
		public BigDecimal getFloatingRateMultiplier() {
			return floatingRateMultiplier;
		}
		
		@Override
		@RosettaAttribute("spread")
		public BigDecimal getSpread() {
			return spread;
		}
		
		@Override
		@RosettaAttribute("capRate")
		public List<? extends Strike> getCapRate() {
			return capRate;
		}
		
		@Override
		@RosettaAttribute("floorRate")
		public List<? extends Strike> getFloorRate() {
			return floorRate;
		}
		
		@Override
		public FloatingRateDefinition build() {
			return this;
		}
		
		@Override
		public FloatingRateDefinition.FloatingRateDefinitionBuilder toBuilder() {
			FloatingRateDefinition.FloatingRateDefinitionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FloatingRateDefinition.FloatingRateDefinitionBuilder builder) {
			ofNullable(getCalculatedRate()).ifPresent(builder::setCalculatedRate);
			ofNullable(getRateObservation()).ifPresent(builder::setRateObservation);
			ofNullable(getFloatingRateMultiplier()).ifPresent(builder::setFloatingRateMultiplier);
			ofNullable(getSpread()).ifPresent(builder::setSpread);
			ofNullable(getCapRate()).ifPresent(builder::setCapRate);
			ofNullable(getFloorRate()).ifPresent(builder::setFloorRate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FloatingRateDefinition _that = getType().cast(o);
		
			if (!Objects.equals(calculatedRate, _that.getCalculatedRate())) return false;
			if (!ListEquals.listEquals(rateObservation, _that.getRateObservation())) return false;
			if (!Objects.equals(floatingRateMultiplier, _that.getFloatingRateMultiplier())) return false;
			if (!Objects.equals(spread, _that.getSpread())) return false;
			if (!ListEquals.listEquals(capRate, _that.getCapRate())) return false;
			if (!ListEquals.listEquals(floorRate, _that.getFloorRate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (calculatedRate != null ? calculatedRate.hashCode() : 0);
			_result = 31 * _result + (rateObservation != null ? rateObservation.hashCode() : 0);
			_result = 31 * _result + (floatingRateMultiplier != null ? floatingRateMultiplier.hashCode() : 0);
			_result = 31 * _result + (spread != null ? spread.hashCode() : 0);
			_result = 31 * _result + (capRate != null ? capRate.hashCode() : 0);
			_result = 31 * _result + (floorRate != null ? floorRate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FloatingRateDefinition {" +
				"calculatedRate=" + this.calculatedRate + ", " +
				"rateObservation=" + this.rateObservation + ", " +
				"floatingRateMultiplier=" + this.floatingRateMultiplier + ", " +
				"spread=" + this.spread + ", " +
				"capRate=" + this.capRate + ", " +
				"floorRate=" + this.floorRate +
			'}';
		}
	}

	/*********************** Builder Implementation of FloatingRateDefinition  ***********************/
	class FloatingRateDefinitionBuilderImpl implements FloatingRateDefinition.FloatingRateDefinitionBuilder {
	
		protected BigDecimal calculatedRate;
		protected List<RateObservation.RateObservationBuilder> rateObservation = new ArrayList<>();
		protected BigDecimal floatingRateMultiplier;
		protected BigDecimal spread;
		protected List<Strike.StrikeBuilder> capRate = new ArrayList<>();
		protected List<Strike.StrikeBuilder> floorRate = new ArrayList<>();
	
		public FloatingRateDefinitionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("calculatedRate")
		public BigDecimal getCalculatedRate() {
			return calculatedRate;
		}
		
		@Override
		@RosettaAttribute("rateObservation")
		public List<? extends RateObservation.RateObservationBuilder> getRateObservation() {
			return rateObservation;
		}
		
		public RateObservation.RateObservationBuilder getOrCreateRateObservation(int _index) {
		
			if (rateObservation==null) {
				this.rateObservation = new ArrayList<>();
			}
			RateObservation.RateObservationBuilder result;
			return getIndex(rateObservation, _index, () -> {
						RateObservation.RateObservationBuilder newRateObservation = RateObservation.builder();
						return newRateObservation;
					});
		}
		
		@Override
		@RosettaAttribute("floatingRateMultiplier")
		public BigDecimal getFloatingRateMultiplier() {
			return floatingRateMultiplier;
		}
		
		@Override
		@RosettaAttribute("spread")
		public BigDecimal getSpread() {
			return spread;
		}
		
		@Override
		@RosettaAttribute("capRate")
		public List<? extends Strike.StrikeBuilder> getCapRate() {
			return capRate;
		}
		
		public Strike.StrikeBuilder getOrCreateCapRate(int _index) {
		
			if (capRate==null) {
				this.capRate = new ArrayList<>();
			}
			Strike.StrikeBuilder result;
			return getIndex(capRate, _index, () -> {
						Strike.StrikeBuilder newCapRate = Strike.builder();
						return newCapRate;
					});
		}
		
		@Override
		@RosettaAttribute("floorRate")
		public List<? extends Strike.StrikeBuilder> getFloorRate() {
			return floorRate;
		}
		
		public Strike.StrikeBuilder getOrCreateFloorRate(int _index) {
		
			if (floorRate==null) {
				this.floorRate = new ArrayList<>();
			}
			Strike.StrikeBuilder result;
			return getIndex(floorRate, _index, () -> {
						Strike.StrikeBuilder newFloorRate = Strike.builder();
						return newFloorRate;
					});
		}
		
	
		@Override
		@RosettaAttribute("calculatedRate")
		public FloatingRateDefinition.FloatingRateDefinitionBuilder setCalculatedRate(BigDecimal calculatedRate) {
			this.calculatedRate = calculatedRate==null?null:calculatedRate;
			return this;
		}
		@Override
		public FloatingRateDefinition.FloatingRateDefinitionBuilder addRateObservation(RateObservation rateObservation) {
			if (rateObservation!=null) this.rateObservation.add(rateObservation.toBuilder());
			return this;
		}
		
		@Override
		public FloatingRateDefinition.FloatingRateDefinitionBuilder addRateObservation(RateObservation rateObservation, int _idx) {
			getIndex(this.rateObservation, _idx, () -> rateObservation.toBuilder());
			return this;
		}
		@Override 
		public FloatingRateDefinition.FloatingRateDefinitionBuilder addRateObservation(List<? extends RateObservation> rateObservations) {
			if (rateObservations != null) {
				for (RateObservation toAdd : rateObservations) {
					this.rateObservation.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("rateObservation")
		public FloatingRateDefinition.FloatingRateDefinitionBuilder setRateObservation(List<? extends RateObservation> rateObservations) {
			if (rateObservations == null)  {
				this.rateObservation = new ArrayList<>();
			}
			else {
				this.rateObservation = rateObservations.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("floatingRateMultiplier")
		public FloatingRateDefinition.FloatingRateDefinitionBuilder setFloatingRateMultiplier(BigDecimal floatingRateMultiplier) {
			this.floatingRateMultiplier = floatingRateMultiplier==null?null:floatingRateMultiplier;
			return this;
		}
		@Override
		@RosettaAttribute("spread")
		public FloatingRateDefinition.FloatingRateDefinitionBuilder setSpread(BigDecimal spread) {
			this.spread = spread==null?null:spread;
			return this;
		}
		@Override
		public FloatingRateDefinition.FloatingRateDefinitionBuilder addCapRate(Strike capRate) {
			if (capRate!=null) this.capRate.add(capRate.toBuilder());
			return this;
		}
		
		@Override
		public FloatingRateDefinition.FloatingRateDefinitionBuilder addCapRate(Strike capRate, int _idx) {
			getIndex(this.capRate, _idx, () -> capRate.toBuilder());
			return this;
		}
		@Override 
		public FloatingRateDefinition.FloatingRateDefinitionBuilder addCapRate(List<? extends Strike> capRates) {
			if (capRates != null) {
				for (Strike toAdd : capRates) {
					this.capRate.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("capRate")
		public FloatingRateDefinition.FloatingRateDefinitionBuilder setCapRate(List<? extends Strike> capRates) {
			if (capRates == null)  {
				this.capRate = new ArrayList<>();
			}
			else {
				this.capRate = capRates.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public FloatingRateDefinition.FloatingRateDefinitionBuilder addFloorRate(Strike floorRate) {
			if (floorRate!=null) this.floorRate.add(floorRate.toBuilder());
			return this;
		}
		
		@Override
		public FloatingRateDefinition.FloatingRateDefinitionBuilder addFloorRate(Strike floorRate, int _idx) {
			getIndex(this.floorRate, _idx, () -> floorRate.toBuilder());
			return this;
		}
		@Override 
		public FloatingRateDefinition.FloatingRateDefinitionBuilder addFloorRate(List<? extends Strike> floorRates) {
			if (floorRates != null) {
				for (Strike toAdd : floorRates) {
					this.floorRate.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("floorRate")
		public FloatingRateDefinition.FloatingRateDefinitionBuilder setFloorRate(List<? extends Strike> floorRates) {
			if (floorRates == null)  {
				this.floorRate = new ArrayList<>();
			}
			else {
				this.floorRate = floorRates.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public FloatingRateDefinition build() {
			return new FloatingRateDefinition.FloatingRateDefinitionImpl(this);
		}
		
		@Override
		public FloatingRateDefinition.FloatingRateDefinitionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FloatingRateDefinition.FloatingRateDefinitionBuilder prune() {
			rateObservation = rateObservation.stream().filter(b->b!=null).<RateObservation.RateObservationBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			capRate = capRate.stream().filter(b->b!=null).<Strike.StrikeBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			floorRate = floorRate.stream().filter(b->b!=null).<Strike.StrikeBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCalculatedRate()!=null) return true;
			if (getRateObservation()!=null && getRateObservation().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getFloatingRateMultiplier()!=null) return true;
			if (getSpread()!=null) return true;
			if (getCapRate()!=null && getCapRate().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getFloorRate()!=null && getFloorRate().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FloatingRateDefinition.FloatingRateDefinitionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FloatingRateDefinition.FloatingRateDefinitionBuilder o = (FloatingRateDefinition.FloatingRateDefinitionBuilder) other;
			
			merger.mergeRosetta(getRateObservation(), o.getRateObservation(), this::getOrCreateRateObservation);
			merger.mergeRosetta(getCapRate(), o.getCapRate(), this::getOrCreateCapRate);
			merger.mergeRosetta(getFloorRate(), o.getFloorRate(), this::getOrCreateFloorRate);
			
			merger.mergeBasic(getCalculatedRate(), o.getCalculatedRate(), this::setCalculatedRate);
			merger.mergeBasic(getFloatingRateMultiplier(), o.getFloatingRateMultiplier(), this::setFloatingRateMultiplier);
			merger.mergeBasic(getSpread(), o.getSpread(), this::setSpread);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FloatingRateDefinition _that = getType().cast(o);
		
			if (!Objects.equals(calculatedRate, _that.getCalculatedRate())) return false;
			if (!ListEquals.listEquals(rateObservation, _that.getRateObservation())) return false;
			if (!Objects.equals(floatingRateMultiplier, _that.getFloatingRateMultiplier())) return false;
			if (!Objects.equals(spread, _that.getSpread())) return false;
			if (!ListEquals.listEquals(capRate, _that.getCapRate())) return false;
			if (!ListEquals.listEquals(floorRate, _that.getFloorRate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (calculatedRate != null ? calculatedRate.hashCode() : 0);
			_result = 31 * _result + (rateObservation != null ? rateObservation.hashCode() : 0);
			_result = 31 * _result + (floatingRateMultiplier != null ? floatingRateMultiplier.hashCode() : 0);
			_result = 31 * _result + (spread != null ? spread.hashCode() : 0);
			_result = 31 * _result + (capRate != null ? capRate.hashCode() : 0);
			_result = 31 * _result + (floorRate != null ? floorRate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FloatingRateDefinitionBuilder {" +
				"calculatedRate=" + this.calculatedRate + ", " +
				"rateObservation=" + this.rateObservation + ", " +
				"floatingRateMultiplier=" + this.floatingRateMultiplier + ", " +
				"spread=" + this.spread + ", " +
				"capRate=" + this.capRate + ", " +
				"floorRate=" + this.floorRate +
			'}';
		}
	}
}
