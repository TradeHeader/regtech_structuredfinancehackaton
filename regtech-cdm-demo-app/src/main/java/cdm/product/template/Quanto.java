package cdm.product.template;

import cdm.base.datetime.BusinessCenterTime;
import cdm.observable.asset.FxRate;
import cdm.observable.asset.FxSpotRateSource;
import cdm.product.template.Quanto;
import cdm.product.template.Quanto.QuantoBuilder;
import cdm.product.template.Quanto.QuantoBuilderImpl;
import cdm.product.template.Quanto.QuantoImpl;
import cdm.product.template.meta.QuantoMeta;
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
 * Determines the currency rate that the seller of the equity amounts will apply at each valuation date for converting the respective amounts into a currency that is different from the currency denomination of the underlier.
 * @version ${project.version}
 */
@RosettaDataType(value="Quanto", builder=Quanto.QuantoBuilderImpl.class, version="${project.version}")
public interface Quanto extends RosettaModelObject {

	QuantoMeta metaData = new QuantoMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies a currency conversion rate.
	 */
	List<? extends FxRate> getFxRate();
	/**
	 * Specifies the methodology (reference source and, optionally, fixing time) to be used for determining a currency conversion rate.
	 */
	FxSpotRateSource getFxSpotRateSource();
	/**
	 * The time at which the spot currency exchange rate will be observed. It is specified as a time in a business day calendar location, e.g. 11:00am London time.
	 */
	BusinessCenterTime getFixingTime();

	/*********************** Build Methods  ***********************/
	Quanto build();
	
	Quanto.QuantoBuilder toBuilder();
	
	static Quanto.QuantoBuilder builder() {
		return new Quanto.QuantoBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Quanto> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Quanto> getType() {
		return Quanto.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("fxRate"), processor, FxRate.class, getFxRate());
		processRosetta(path.newSubPath("fxSpotRateSource"), processor, FxSpotRateSource.class, getFxSpotRateSource());
		processRosetta(path.newSubPath("fixingTime"), processor, BusinessCenterTime.class, getFixingTime());
	}
	

	/*********************** Builder Interface  ***********************/
	interface QuantoBuilder extends Quanto, RosettaModelObjectBuilder {
		FxRate.FxRateBuilder getOrCreateFxRate(int _index);
		List<? extends FxRate.FxRateBuilder> getFxRate();
		FxSpotRateSource.FxSpotRateSourceBuilder getOrCreateFxSpotRateSource();
		FxSpotRateSource.FxSpotRateSourceBuilder getFxSpotRateSource();
		BusinessCenterTime.BusinessCenterTimeBuilder getOrCreateFixingTime();
		BusinessCenterTime.BusinessCenterTimeBuilder getFixingTime();
		Quanto.QuantoBuilder addFxRate(FxRate fxRate0);
		Quanto.QuantoBuilder addFxRate(FxRate fxRate1, int _idx);
		Quanto.QuantoBuilder addFxRate(List<? extends FxRate> fxRate2);
		Quanto.QuantoBuilder setFxRate(List<? extends FxRate> fxRate3);
		Quanto.QuantoBuilder setFxSpotRateSource(FxSpotRateSource fxSpotRateSource);
		Quanto.QuantoBuilder setFixingTime(BusinessCenterTime fixingTime);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("fxRate"), processor, FxRate.FxRateBuilder.class, getFxRate());
			processRosetta(path.newSubPath("fxSpotRateSource"), processor, FxSpotRateSource.FxSpotRateSourceBuilder.class, getFxSpotRateSource());
			processRosetta(path.newSubPath("fixingTime"), processor, BusinessCenterTime.BusinessCenterTimeBuilder.class, getFixingTime());
		}
		

		Quanto.QuantoBuilder prune();
	}

	/*********************** Immutable Implementation of Quanto  ***********************/
	class QuantoImpl implements Quanto {
		private final List<? extends FxRate> fxRate;
		private final FxSpotRateSource fxSpotRateSource;
		private final BusinessCenterTime fixingTime;
		
		protected QuantoImpl(Quanto.QuantoBuilder builder) {
			this.fxRate = ofNullable(builder.getFxRate()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.fxSpotRateSource = ofNullable(builder.getFxSpotRateSource()).map(f->f.build()).orElse(null);
			this.fixingTime = ofNullable(builder.getFixingTime()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("fxRate")
		public List<? extends FxRate> getFxRate() {
			return fxRate;
		}
		
		@Override
		@RosettaAttribute("fxSpotRateSource")
		public FxSpotRateSource getFxSpotRateSource() {
			return fxSpotRateSource;
		}
		
		@Override
		@RosettaAttribute("fixingTime")
		public BusinessCenterTime getFixingTime() {
			return fixingTime;
		}
		
		@Override
		public Quanto build() {
			return this;
		}
		
		@Override
		public Quanto.QuantoBuilder toBuilder() {
			Quanto.QuantoBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Quanto.QuantoBuilder builder) {
			ofNullable(getFxRate()).ifPresent(builder::setFxRate);
			ofNullable(getFxSpotRateSource()).ifPresent(builder::setFxSpotRateSource);
			ofNullable(getFixingTime()).ifPresent(builder::setFixingTime);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Quanto _that = getType().cast(o);
		
			if (!ListEquals.listEquals(fxRate, _that.getFxRate())) return false;
			if (!Objects.equals(fxSpotRateSource, _that.getFxSpotRateSource())) return false;
			if (!Objects.equals(fixingTime, _that.getFixingTime())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (fxRate != null ? fxRate.hashCode() : 0);
			_result = 31 * _result + (fxSpotRateSource != null ? fxSpotRateSource.hashCode() : 0);
			_result = 31 * _result + (fixingTime != null ? fixingTime.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Quanto {" +
				"fxRate=" + this.fxRate + ", " +
				"fxSpotRateSource=" + this.fxSpotRateSource + ", " +
				"fixingTime=" + this.fixingTime +
			'}';
		}
	}

	/*********************** Builder Implementation of Quanto  ***********************/
	class QuantoBuilderImpl implements Quanto.QuantoBuilder {
	
		protected List<FxRate.FxRateBuilder> fxRate = new ArrayList<>();
		protected FxSpotRateSource.FxSpotRateSourceBuilder fxSpotRateSource;
		protected BusinessCenterTime.BusinessCenterTimeBuilder fixingTime;
	
		public QuantoBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("fxRate")
		public List<? extends FxRate.FxRateBuilder> getFxRate() {
			return fxRate;
		}
		
		public FxRate.FxRateBuilder getOrCreateFxRate(int _index) {
		
			if (fxRate==null) {
				this.fxRate = new ArrayList<>();
			}
			FxRate.FxRateBuilder result;
			return getIndex(fxRate, _index, () -> {
						FxRate.FxRateBuilder newFxRate = FxRate.builder();
						return newFxRate;
					});
		}
		
		@Override
		@RosettaAttribute("fxSpotRateSource")
		public FxSpotRateSource.FxSpotRateSourceBuilder getFxSpotRateSource() {
			return fxSpotRateSource;
		}
		
		@Override
		public FxSpotRateSource.FxSpotRateSourceBuilder getOrCreateFxSpotRateSource() {
			FxSpotRateSource.FxSpotRateSourceBuilder result;
			if (fxSpotRateSource!=null) {
				result = fxSpotRateSource;
			}
			else {
				result = fxSpotRateSource = FxSpotRateSource.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("fixingTime")
		public BusinessCenterTime.BusinessCenterTimeBuilder getFixingTime() {
			return fixingTime;
		}
		
		@Override
		public BusinessCenterTime.BusinessCenterTimeBuilder getOrCreateFixingTime() {
			BusinessCenterTime.BusinessCenterTimeBuilder result;
			if (fixingTime!=null) {
				result = fixingTime;
			}
			else {
				result = fixingTime = BusinessCenterTime.builder();
			}
			
			return result;
		}
	
		@Override
		public Quanto.QuantoBuilder addFxRate(FxRate fxRate) {
			if (fxRate!=null) this.fxRate.add(fxRate.toBuilder());
			return this;
		}
		
		@Override
		public Quanto.QuantoBuilder addFxRate(FxRate fxRate, int _idx) {
			getIndex(this.fxRate, _idx, () -> fxRate.toBuilder());
			return this;
		}
		@Override 
		public Quanto.QuantoBuilder addFxRate(List<? extends FxRate> fxRates) {
			if (fxRates != null) {
				for (FxRate toAdd : fxRates) {
					this.fxRate.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("fxRate")
		public Quanto.QuantoBuilder setFxRate(List<? extends FxRate> fxRates) {
			if (fxRates == null)  {
				this.fxRate = new ArrayList<>();
			}
			else {
				this.fxRate = fxRates.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("fxSpotRateSource")
		public Quanto.QuantoBuilder setFxSpotRateSource(FxSpotRateSource fxSpotRateSource) {
			this.fxSpotRateSource = fxSpotRateSource==null?null:fxSpotRateSource.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("fixingTime")
		public Quanto.QuantoBuilder setFixingTime(BusinessCenterTime fixingTime) {
			this.fixingTime = fixingTime==null?null:fixingTime.toBuilder();
			return this;
		}
		
		@Override
		public Quanto build() {
			return new Quanto.QuantoImpl(this);
		}
		
		@Override
		public Quanto.QuantoBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Quanto.QuantoBuilder prune() {
			fxRate = fxRate.stream().filter(b->b!=null).<FxRate.FxRateBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (fxSpotRateSource!=null && !fxSpotRateSource.prune().hasData()) fxSpotRateSource = null;
			if (fixingTime!=null && !fixingTime.prune().hasData()) fixingTime = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getFxRate()!=null && getFxRate().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getFxSpotRateSource()!=null && getFxSpotRateSource().hasData()) return true;
			if (getFixingTime()!=null && getFixingTime().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Quanto.QuantoBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Quanto.QuantoBuilder o = (Quanto.QuantoBuilder) other;
			
			merger.mergeRosetta(getFxRate(), o.getFxRate(), this::getOrCreateFxRate);
			merger.mergeRosetta(getFxSpotRateSource(), o.getFxSpotRateSource(), this::setFxSpotRateSource);
			merger.mergeRosetta(getFixingTime(), o.getFixingTime(), this::setFixingTime);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Quanto _that = getType().cast(o);
		
			if (!ListEquals.listEquals(fxRate, _that.getFxRate())) return false;
			if (!Objects.equals(fxSpotRateSource, _that.getFxSpotRateSource())) return false;
			if (!Objects.equals(fixingTime, _that.getFixingTime())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (fxRate != null ? fxRate.hashCode() : 0);
			_result = 31 * _result + (fxSpotRateSource != null ? fxSpotRateSource.hashCode() : 0);
			_result = 31 * _result + (fixingTime != null ? fixingTime.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "QuantoBuilder {" +
				"fxRate=" + this.fxRate + ", " +
				"fxSpotRateSource=" + this.fxSpotRateSource + ", " +
				"fixingTime=" + this.fixingTime +
			'}';
		}
	}
}
