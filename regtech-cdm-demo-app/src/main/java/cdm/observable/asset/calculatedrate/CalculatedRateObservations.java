package cdm.observable.asset.calculatedrate;

import cdm.observable.asset.calculatedrate.CalculatedRateObservations;
import cdm.observable.asset.calculatedrate.CalculatedRateObservations.CalculatedRateObservationsBuilder;
import cdm.observable.asset.calculatedrate.CalculatedRateObservations.CalculatedRateObservationsBuilderImpl;
import cdm.observable.asset.calculatedrate.CalculatedRateObservations.CalculatedRateObservationsImpl;
import cdm.observable.asset.calculatedrate.meta.CalculatedRateObservationsMeta;
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
import com.rosetta.model.lib.records.Date;
import com.rosetta.util.ListEquals;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Type for reporting observations that went into the final reported rate.
 * @version ${project.version}
 */
@RosettaDataType(value="CalculatedRateObservations", builder=CalculatedRateObservations.CalculatedRateObservationsBuilderImpl.class, version="${project.version}")
public interface CalculatedRateObservations extends RosettaModelObject {

	CalculatedRateObservationsMeta metaData = new CalculatedRateObservationsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The observation date upon which the rate is observed.
	 */
	List<Date> getObservationDates();
	/**
	 * The corresponding weight for each date.
	 */
	List<BigDecimal> getWeights();
	/**
	 * The value observed for that date
	 */
	List<BigDecimal> getObservedRates();
	/**
	 * The value after any processing, such as application of caps or floors.
	 */
	List<BigDecimal> getProcessedRates();

	/*********************** Build Methods  ***********************/
	CalculatedRateObservations build();
	
	CalculatedRateObservations.CalculatedRateObservationsBuilder toBuilder();
	
	static CalculatedRateObservations.CalculatedRateObservationsBuilder builder() {
		return new CalculatedRateObservations.CalculatedRateObservationsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CalculatedRateObservations> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CalculatedRateObservations> getType() {
		return CalculatedRateObservations.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("observationDates"), Date.class, getObservationDates(), this);
		processor.processBasic(path.newSubPath("weights"), BigDecimal.class, getWeights(), this);
		processor.processBasic(path.newSubPath("observedRates"), BigDecimal.class, getObservedRates(), this);
		processor.processBasic(path.newSubPath("processedRates"), BigDecimal.class, getProcessedRates(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface CalculatedRateObservationsBuilder extends CalculatedRateObservations, RosettaModelObjectBuilder {
		CalculatedRateObservations.CalculatedRateObservationsBuilder addObservationDates(Date observationDates0);
		CalculatedRateObservations.CalculatedRateObservationsBuilder addObservationDates(Date observationDates1, int _idx);
		CalculatedRateObservations.CalculatedRateObservationsBuilder addObservationDates(List<? extends Date> observationDates2);
		CalculatedRateObservations.CalculatedRateObservationsBuilder setObservationDates(List<? extends Date> observationDates3);
		CalculatedRateObservations.CalculatedRateObservationsBuilder addWeights(BigDecimal weights0);
		CalculatedRateObservations.CalculatedRateObservationsBuilder addWeights(BigDecimal weights1, int _idx);
		CalculatedRateObservations.CalculatedRateObservationsBuilder addWeights(List<? extends BigDecimal> weights2);
		CalculatedRateObservations.CalculatedRateObservationsBuilder setWeights(List<? extends BigDecimal> weights3);
		CalculatedRateObservations.CalculatedRateObservationsBuilder addObservedRates(BigDecimal observedRates0);
		CalculatedRateObservations.CalculatedRateObservationsBuilder addObservedRates(BigDecimal observedRates1, int _idx);
		CalculatedRateObservations.CalculatedRateObservationsBuilder addObservedRates(List<? extends BigDecimal> observedRates2);
		CalculatedRateObservations.CalculatedRateObservationsBuilder setObservedRates(List<? extends BigDecimal> observedRates3);
		CalculatedRateObservations.CalculatedRateObservationsBuilder addProcessedRates(BigDecimal processedRates0);
		CalculatedRateObservations.CalculatedRateObservationsBuilder addProcessedRates(BigDecimal processedRates1, int _idx);
		CalculatedRateObservations.CalculatedRateObservationsBuilder addProcessedRates(List<? extends BigDecimal> processedRates2);
		CalculatedRateObservations.CalculatedRateObservationsBuilder setProcessedRates(List<? extends BigDecimal> processedRates3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("observationDates"), Date.class, getObservationDates(), this);
			processor.processBasic(path.newSubPath("weights"), BigDecimal.class, getWeights(), this);
			processor.processBasic(path.newSubPath("observedRates"), BigDecimal.class, getObservedRates(), this);
			processor.processBasic(path.newSubPath("processedRates"), BigDecimal.class, getProcessedRates(), this);
		}
		

		CalculatedRateObservations.CalculatedRateObservationsBuilder prune();
	}

	/*********************** Immutable Implementation of CalculatedRateObservations  ***********************/
	class CalculatedRateObservationsImpl implements CalculatedRateObservations {
		private final List<Date> observationDates;
		private final List<BigDecimal> weights;
		private final List<BigDecimal> observedRates;
		private final List<BigDecimal> processedRates;
		
		protected CalculatedRateObservationsImpl(CalculatedRateObservations.CalculatedRateObservationsBuilder builder) {
			this.observationDates = ofNullable(builder.getObservationDates()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.weights = ofNullable(builder.getWeights()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.observedRates = ofNullable(builder.getObservedRates()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.processedRates = ofNullable(builder.getProcessedRates()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
		}
		
		@Override
		@RosettaAttribute("observationDates")
		public List<Date> getObservationDates() {
			return observationDates;
		}
		
		@Override
		@RosettaAttribute("weights")
		public List<BigDecimal> getWeights() {
			return weights;
		}
		
		@Override
		@RosettaAttribute("observedRates")
		public List<BigDecimal> getObservedRates() {
			return observedRates;
		}
		
		@Override
		@RosettaAttribute("processedRates")
		public List<BigDecimal> getProcessedRates() {
			return processedRates;
		}
		
		@Override
		public CalculatedRateObservations build() {
			return this;
		}
		
		@Override
		public CalculatedRateObservations.CalculatedRateObservationsBuilder toBuilder() {
			CalculatedRateObservations.CalculatedRateObservationsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CalculatedRateObservations.CalculatedRateObservationsBuilder builder) {
			ofNullable(getObservationDates()).ifPresent(builder::setObservationDates);
			ofNullable(getWeights()).ifPresent(builder::setWeights);
			ofNullable(getObservedRates()).ifPresent(builder::setObservedRates);
			ofNullable(getProcessedRates()).ifPresent(builder::setProcessedRates);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CalculatedRateObservations _that = getType().cast(o);
		
			if (!ListEquals.listEquals(observationDates, _that.getObservationDates())) return false;
			if (!ListEquals.listEquals(weights, _that.getWeights())) return false;
			if (!ListEquals.listEquals(observedRates, _that.getObservedRates())) return false;
			if (!ListEquals.listEquals(processedRates, _that.getProcessedRates())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (observationDates != null ? observationDates.hashCode() : 0);
			_result = 31 * _result + (weights != null ? weights.hashCode() : 0);
			_result = 31 * _result + (observedRates != null ? observedRates.hashCode() : 0);
			_result = 31 * _result + (processedRates != null ? processedRates.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CalculatedRateObservations {" +
				"observationDates=" + this.observationDates + ", " +
				"weights=" + this.weights + ", " +
				"observedRates=" + this.observedRates + ", " +
				"processedRates=" + this.processedRates +
			'}';
		}
	}

	/*********************** Builder Implementation of CalculatedRateObservations  ***********************/
	class CalculatedRateObservationsBuilderImpl implements CalculatedRateObservations.CalculatedRateObservationsBuilder {
	
		protected List<Date> observationDates = new ArrayList<>();
		protected List<BigDecimal> weights = new ArrayList<>();
		protected List<BigDecimal> observedRates = new ArrayList<>();
		protected List<BigDecimal> processedRates = new ArrayList<>();
	
		public CalculatedRateObservationsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("observationDates")
		public List<Date> getObservationDates() {
			return observationDates;
		}
		
		@Override
		@RosettaAttribute("weights")
		public List<BigDecimal> getWeights() {
			return weights;
		}
		
		@Override
		@RosettaAttribute("observedRates")
		public List<BigDecimal> getObservedRates() {
			return observedRates;
		}
		
		@Override
		@RosettaAttribute("processedRates")
		public List<BigDecimal> getProcessedRates() {
			return processedRates;
		}
		
	
		@Override
		public CalculatedRateObservations.CalculatedRateObservationsBuilder addObservationDates(Date observationDates) {
			if (observationDates!=null) this.observationDates.add(observationDates);
			return this;
		}
		
		@Override
		public CalculatedRateObservations.CalculatedRateObservationsBuilder addObservationDates(Date observationDates, int _idx) {
			getIndex(this.observationDates, _idx, () -> observationDates);
			return this;
		}
		@Override 
		public CalculatedRateObservations.CalculatedRateObservationsBuilder addObservationDates(List<? extends Date> observationDatess) {
			if (observationDatess != null) {
				for (Date toAdd : observationDatess) {
					this.observationDates.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("observationDates")
		public CalculatedRateObservations.CalculatedRateObservationsBuilder setObservationDates(List<? extends Date> observationDatess) {
			if (observationDatess == null)  {
				this.observationDates = new ArrayList<>();
			}
			else {
				this.observationDates = observationDatess.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public CalculatedRateObservations.CalculatedRateObservationsBuilder addWeights(BigDecimal weights) {
			if (weights!=null) this.weights.add(weights);
			return this;
		}
		
		@Override
		public CalculatedRateObservations.CalculatedRateObservationsBuilder addWeights(BigDecimal weights, int _idx) {
			getIndex(this.weights, _idx, () -> weights);
			return this;
		}
		@Override 
		public CalculatedRateObservations.CalculatedRateObservationsBuilder addWeights(List<? extends BigDecimal> weightss) {
			if (weightss != null) {
				for (BigDecimal toAdd : weightss) {
					this.weights.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("weights")
		public CalculatedRateObservations.CalculatedRateObservationsBuilder setWeights(List<? extends BigDecimal> weightss) {
			if (weightss == null)  {
				this.weights = new ArrayList<>();
			}
			else {
				this.weights = weightss.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public CalculatedRateObservations.CalculatedRateObservationsBuilder addObservedRates(BigDecimal observedRates) {
			if (observedRates!=null) this.observedRates.add(observedRates);
			return this;
		}
		
		@Override
		public CalculatedRateObservations.CalculatedRateObservationsBuilder addObservedRates(BigDecimal observedRates, int _idx) {
			getIndex(this.observedRates, _idx, () -> observedRates);
			return this;
		}
		@Override 
		public CalculatedRateObservations.CalculatedRateObservationsBuilder addObservedRates(List<? extends BigDecimal> observedRatess) {
			if (observedRatess != null) {
				for (BigDecimal toAdd : observedRatess) {
					this.observedRates.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("observedRates")
		public CalculatedRateObservations.CalculatedRateObservationsBuilder setObservedRates(List<? extends BigDecimal> observedRatess) {
			if (observedRatess == null)  {
				this.observedRates = new ArrayList<>();
			}
			else {
				this.observedRates = observedRatess.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public CalculatedRateObservations.CalculatedRateObservationsBuilder addProcessedRates(BigDecimal processedRates) {
			if (processedRates!=null) this.processedRates.add(processedRates);
			return this;
		}
		
		@Override
		public CalculatedRateObservations.CalculatedRateObservationsBuilder addProcessedRates(BigDecimal processedRates, int _idx) {
			getIndex(this.processedRates, _idx, () -> processedRates);
			return this;
		}
		@Override 
		public CalculatedRateObservations.CalculatedRateObservationsBuilder addProcessedRates(List<? extends BigDecimal> processedRatess) {
			if (processedRatess != null) {
				for (BigDecimal toAdd : processedRatess) {
					this.processedRates.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("processedRates")
		public CalculatedRateObservations.CalculatedRateObservationsBuilder setProcessedRates(List<? extends BigDecimal> processedRatess) {
			if (processedRatess == null)  {
				this.processedRates = new ArrayList<>();
			}
			else {
				this.processedRates = processedRatess.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public CalculatedRateObservations build() {
			return new CalculatedRateObservations.CalculatedRateObservationsImpl(this);
		}
		
		@Override
		public CalculatedRateObservations.CalculatedRateObservationsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CalculatedRateObservations.CalculatedRateObservationsBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getObservationDates()!=null && !getObservationDates().isEmpty()) return true;
			if (getWeights()!=null && !getWeights().isEmpty()) return true;
			if (getObservedRates()!=null && !getObservedRates().isEmpty()) return true;
			if (getProcessedRates()!=null && !getProcessedRates().isEmpty()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CalculatedRateObservations.CalculatedRateObservationsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CalculatedRateObservations.CalculatedRateObservationsBuilder o = (CalculatedRateObservations.CalculatedRateObservationsBuilder) other;
			
			
			merger.mergeBasic(getObservationDates(), o.getObservationDates(), (Consumer<Date>) this::addObservationDates);
			merger.mergeBasic(getWeights(), o.getWeights(), (Consumer<BigDecimal>) this::addWeights);
			merger.mergeBasic(getObservedRates(), o.getObservedRates(), (Consumer<BigDecimal>) this::addObservedRates);
			merger.mergeBasic(getProcessedRates(), o.getProcessedRates(), (Consumer<BigDecimal>) this::addProcessedRates);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CalculatedRateObservations _that = getType().cast(o);
		
			if (!ListEquals.listEquals(observationDates, _that.getObservationDates())) return false;
			if (!ListEquals.listEquals(weights, _that.getWeights())) return false;
			if (!ListEquals.listEquals(observedRates, _that.getObservedRates())) return false;
			if (!ListEquals.listEquals(processedRates, _that.getProcessedRates())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (observationDates != null ? observationDates.hashCode() : 0);
			_result = 31 * _result + (weights != null ? weights.hashCode() : 0);
			_result = 31 * _result + (observedRates != null ? observedRates.hashCode() : 0);
			_result = 31 * _result + (processedRates != null ? processedRates.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CalculatedRateObservationsBuilder {" +
				"observationDates=" + this.observationDates + ", " +
				"weights=" + this.weights + ", " +
				"observedRates=" + this.observedRates + ", " +
				"processedRates=" + this.processedRates +
			'}';
		}
	}
}
