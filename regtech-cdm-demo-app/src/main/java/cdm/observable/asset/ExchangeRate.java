package cdm.observable.asset;

import cdm.observable.asset.CrossRate;
import cdm.observable.asset.ExchangeRate;
import cdm.observable.asset.ExchangeRate.ExchangeRateBuilder;
import cdm.observable.asset.ExchangeRate.ExchangeRateBuilderImpl;
import cdm.observable.asset.ExchangeRate.ExchangeRateImpl;
import cdm.observable.asset.meta.ExchangeRateMeta;
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
 * A class that is used for describing the exchange rate for a particular transaction.
 * @version ${project.version}
 */
@RosettaDataType(value="ExchangeRate", builder=ExchangeRate.ExchangeRateBuilderImpl.class, version="${project.version}")
public interface ExchangeRate extends RosettaModelObject {

	ExchangeRateMeta metaData = new ExchangeRateMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * An optional element that allow for definition of the currency exchange rates used to cross between the traded currencies for non-base currency FX contracts.
	 */
	List<? extends CrossRate> getCrossRate();

	/*********************** Build Methods  ***********************/
	ExchangeRate build();
	
	ExchangeRate.ExchangeRateBuilder toBuilder();
	
	static ExchangeRate.ExchangeRateBuilder builder() {
		return new ExchangeRate.ExchangeRateBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ExchangeRate> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ExchangeRate> getType() {
		return ExchangeRate.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("crossRate"), processor, CrossRate.class, getCrossRate());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ExchangeRateBuilder extends ExchangeRate, RosettaModelObjectBuilder {
		CrossRate.CrossRateBuilder getOrCreateCrossRate(int _index);
		List<? extends CrossRate.CrossRateBuilder> getCrossRate();
		ExchangeRate.ExchangeRateBuilder addCrossRate(CrossRate crossRate0);
		ExchangeRate.ExchangeRateBuilder addCrossRate(CrossRate crossRate1, int _idx);
		ExchangeRate.ExchangeRateBuilder addCrossRate(List<? extends CrossRate> crossRate2);
		ExchangeRate.ExchangeRateBuilder setCrossRate(List<? extends CrossRate> crossRate3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("crossRate"), processor, CrossRate.CrossRateBuilder.class, getCrossRate());
		}
		

		ExchangeRate.ExchangeRateBuilder prune();
	}

	/*********************** Immutable Implementation of ExchangeRate  ***********************/
	class ExchangeRateImpl implements ExchangeRate {
		private final List<? extends CrossRate> crossRate;
		
		protected ExchangeRateImpl(ExchangeRate.ExchangeRateBuilder builder) {
			this.crossRate = ofNullable(builder.getCrossRate()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("crossRate")
		public List<? extends CrossRate> getCrossRate() {
			return crossRate;
		}
		
		@Override
		public ExchangeRate build() {
			return this;
		}
		
		@Override
		public ExchangeRate.ExchangeRateBuilder toBuilder() {
			ExchangeRate.ExchangeRateBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ExchangeRate.ExchangeRateBuilder builder) {
			ofNullable(getCrossRate()).ifPresent(builder::setCrossRate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ExchangeRate _that = getType().cast(o);
		
			if (!ListEquals.listEquals(crossRate, _that.getCrossRate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (crossRate != null ? crossRate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ExchangeRate {" +
				"crossRate=" + this.crossRate +
			'}';
		}
	}

	/*********************** Builder Implementation of ExchangeRate  ***********************/
	class ExchangeRateBuilderImpl implements ExchangeRate.ExchangeRateBuilder {
	
		protected List<CrossRate.CrossRateBuilder> crossRate = new ArrayList<>();
	
		public ExchangeRateBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("crossRate")
		public List<? extends CrossRate.CrossRateBuilder> getCrossRate() {
			return crossRate;
		}
		
		public CrossRate.CrossRateBuilder getOrCreateCrossRate(int _index) {
		
			if (crossRate==null) {
				this.crossRate = new ArrayList<>();
			}
			CrossRate.CrossRateBuilder result;
			return getIndex(crossRate, _index, () -> {
						CrossRate.CrossRateBuilder newCrossRate = CrossRate.builder();
						return newCrossRate;
					});
		}
		
	
		@Override
		public ExchangeRate.ExchangeRateBuilder addCrossRate(CrossRate crossRate) {
			if (crossRate!=null) this.crossRate.add(crossRate.toBuilder());
			return this;
		}
		
		@Override
		public ExchangeRate.ExchangeRateBuilder addCrossRate(CrossRate crossRate, int _idx) {
			getIndex(this.crossRate, _idx, () -> crossRate.toBuilder());
			return this;
		}
		@Override 
		public ExchangeRate.ExchangeRateBuilder addCrossRate(List<? extends CrossRate> crossRates) {
			if (crossRates != null) {
				for (CrossRate toAdd : crossRates) {
					this.crossRate.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("crossRate")
		public ExchangeRate.ExchangeRateBuilder setCrossRate(List<? extends CrossRate> crossRates) {
			if (crossRates == null)  {
				this.crossRate = new ArrayList<>();
			}
			else {
				this.crossRate = crossRates.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public ExchangeRate build() {
			return new ExchangeRate.ExchangeRateImpl(this);
		}
		
		@Override
		public ExchangeRate.ExchangeRateBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ExchangeRate.ExchangeRateBuilder prune() {
			crossRate = crossRate.stream().filter(b->b!=null).<CrossRate.CrossRateBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCrossRate()!=null && getCrossRate().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ExchangeRate.ExchangeRateBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ExchangeRate.ExchangeRateBuilder o = (ExchangeRate.ExchangeRateBuilder) other;
			
			merger.mergeRosetta(getCrossRate(), o.getCrossRate(), this::getOrCreateCrossRate);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ExchangeRate _that = getType().cast(o);
		
			if (!ListEquals.listEquals(crossRate, _that.getCrossRate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (crossRate != null ? crossRate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ExchangeRateBuilder {" +
				"crossRate=" + this.crossRate +
			'}';
		}
	}
}
