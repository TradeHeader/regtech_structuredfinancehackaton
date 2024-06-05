package cdm.product.asset;

import cdm.product.asset.FixedRateSpecification;
import cdm.product.asset.FloatingRateSpecification;
import cdm.product.asset.InflationRateSpecification;
import cdm.product.asset.RateSpecification;
import cdm.product.asset.RateSpecification.RateSpecificationBuilder;
import cdm.product.asset.RateSpecification.RateSpecificationBuilderImpl;
import cdm.product.asset.RateSpecification.RateSpecificationImpl;
import cdm.product.asset.meta.RateSpecificationMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 *  A class to specify the fixed interest rate, floating interest rate or inflation rate.
 * @version ${project.version}
 */
@RosettaDataType(value="RateSpecification", builder=RateSpecification.RateSpecificationBuilderImpl.class, version="${project.version}")
public interface RateSpecification extends RosettaModelObject {

	RateSpecificationMeta metaData = new RateSpecificationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The fixed rate or fixed rate specification expressed as explicit fixed rates and dates.
	 */
	FixedRateSpecification getFixedRate();
	/**
	 * The floating interest rate specification, which includes the definition of the floating rate index. the tenor, the initial value, and, when applicable, the spread, the rounding convention, the averaging method and the negative interest rate treatment.
	 */
	FloatingRateSpecification getFloatingRate();
	/**
	 * An inflation rate calculation definition.
	 */
	InflationRateSpecification getInflationRate();

	/*********************** Build Methods  ***********************/
	RateSpecification build();
	
	RateSpecification.RateSpecificationBuilder toBuilder();
	
	static RateSpecification.RateSpecificationBuilder builder() {
		return new RateSpecification.RateSpecificationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends RateSpecification> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends RateSpecification> getType() {
		return RateSpecification.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("fixedRate"), processor, FixedRateSpecification.class, getFixedRate());
		processRosetta(path.newSubPath("floatingRate"), processor, FloatingRateSpecification.class, getFloatingRate());
		processRosetta(path.newSubPath("inflationRate"), processor, InflationRateSpecification.class, getInflationRate());
	}
	

	/*********************** Builder Interface  ***********************/
	interface RateSpecificationBuilder extends RateSpecification, RosettaModelObjectBuilder {
		FixedRateSpecification.FixedRateSpecificationBuilder getOrCreateFixedRate();
		FixedRateSpecification.FixedRateSpecificationBuilder getFixedRate();
		FloatingRateSpecification.FloatingRateSpecificationBuilder getOrCreateFloatingRate();
		FloatingRateSpecification.FloatingRateSpecificationBuilder getFloatingRate();
		InflationRateSpecification.InflationRateSpecificationBuilder getOrCreateInflationRate();
		InflationRateSpecification.InflationRateSpecificationBuilder getInflationRate();
		RateSpecification.RateSpecificationBuilder setFixedRate(FixedRateSpecification fixedRate);
		RateSpecification.RateSpecificationBuilder setFloatingRate(FloatingRateSpecification floatingRate);
		RateSpecification.RateSpecificationBuilder setInflationRate(InflationRateSpecification inflationRate);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("fixedRate"), processor, FixedRateSpecification.FixedRateSpecificationBuilder.class, getFixedRate());
			processRosetta(path.newSubPath("floatingRate"), processor, FloatingRateSpecification.FloatingRateSpecificationBuilder.class, getFloatingRate());
			processRosetta(path.newSubPath("inflationRate"), processor, InflationRateSpecification.InflationRateSpecificationBuilder.class, getInflationRate());
		}
		

		RateSpecification.RateSpecificationBuilder prune();
	}

	/*********************** Immutable Implementation of RateSpecification  ***********************/
	class RateSpecificationImpl implements RateSpecification {
		private final FixedRateSpecification fixedRate;
		private final FloatingRateSpecification floatingRate;
		private final InflationRateSpecification inflationRate;
		
		protected RateSpecificationImpl(RateSpecification.RateSpecificationBuilder builder) {
			this.fixedRate = ofNullable(builder.getFixedRate()).map(f->f.build()).orElse(null);
			this.floatingRate = ofNullable(builder.getFloatingRate()).map(f->f.build()).orElse(null);
			this.inflationRate = ofNullable(builder.getInflationRate()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("fixedRate")
		public FixedRateSpecification getFixedRate() {
			return fixedRate;
		}
		
		@Override
		@RosettaAttribute("floatingRate")
		public FloatingRateSpecification getFloatingRate() {
			return floatingRate;
		}
		
		@Override
		@RosettaAttribute("inflationRate")
		public InflationRateSpecification getInflationRate() {
			return inflationRate;
		}
		
		@Override
		public RateSpecification build() {
			return this;
		}
		
		@Override
		public RateSpecification.RateSpecificationBuilder toBuilder() {
			RateSpecification.RateSpecificationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(RateSpecification.RateSpecificationBuilder builder) {
			ofNullable(getFixedRate()).ifPresent(builder::setFixedRate);
			ofNullable(getFloatingRate()).ifPresent(builder::setFloatingRate);
			ofNullable(getInflationRate()).ifPresent(builder::setInflationRate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			RateSpecification _that = getType().cast(o);
		
			if (!Objects.equals(fixedRate, _that.getFixedRate())) return false;
			if (!Objects.equals(floatingRate, _that.getFloatingRate())) return false;
			if (!Objects.equals(inflationRate, _that.getInflationRate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (fixedRate != null ? fixedRate.hashCode() : 0);
			_result = 31 * _result + (floatingRate != null ? floatingRate.hashCode() : 0);
			_result = 31 * _result + (inflationRate != null ? inflationRate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "RateSpecification {" +
				"fixedRate=" + this.fixedRate + ", " +
				"floatingRate=" + this.floatingRate + ", " +
				"inflationRate=" + this.inflationRate +
			'}';
		}
	}

	/*********************** Builder Implementation of RateSpecification  ***********************/
	class RateSpecificationBuilderImpl implements RateSpecification.RateSpecificationBuilder {
	
		protected FixedRateSpecification.FixedRateSpecificationBuilder fixedRate;
		protected FloatingRateSpecification.FloatingRateSpecificationBuilder floatingRate;
		protected InflationRateSpecification.InflationRateSpecificationBuilder inflationRate;
	
		public RateSpecificationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("fixedRate")
		public FixedRateSpecification.FixedRateSpecificationBuilder getFixedRate() {
			return fixedRate;
		}
		
		@Override
		public FixedRateSpecification.FixedRateSpecificationBuilder getOrCreateFixedRate() {
			FixedRateSpecification.FixedRateSpecificationBuilder result;
			if (fixedRate!=null) {
				result = fixedRate;
			}
			else {
				result = fixedRate = FixedRateSpecification.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("floatingRate")
		public FloatingRateSpecification.FloatingRateSpecificationBuilder getFloatingRate() {
			return floatingRate;
		}
		
		@Override
		public FloatingRateSpecification.FloatingRateSpecificationBuilder getOrCreateFloatingRate() {
			FloatingRateSpecification.FloatingRateSpecificationBuilder result;
			if (floatingRate!=null) {
				result = floatingRate;
			}
			else {
				result = floatingRate = FloatingRateSpecification.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("inflationRate")
		public InflationRateSpecification.InflationRateSpecificationBuilder getInflationRate() {
			return inflationRate;
		}
		
		@Override
		public InflationRateSpecification.InflationRateSpecificationBuilder getOrCreateInflationRate() {
			InflationRateSpecification.InflationRateSpecificationBuilder result;
			if (inflationRate!=null) {
				result = inflationRate;
			}
			else {
				result = inflationRate = InflationRateSpecification.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("fixedRate")
		public RateSpecification.RateSpecificationBuilder setFixedRate(FixedRateSpecification fixedRate) {
			this.fixedRate = fixedRate==null?null:fixedRate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("floatingRate")
		public RateSpecification.RateSpecificationBuilder setFloatingRate(FloatingRateSpecification floatingRate) {
			this.floatingRate = floatingRate==null?null:floatingRate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("inflationRate")
		public RateSpecification.RateSpecificationBuilder setInflationRate(InflationRateSpecification inflationRate) {
			this.inflationRate = inflationRate==null?null:inflationRate.toBuilder();
			return this;
		}
		
		@Override
		public RateSpecification build() {
			return new RateSpecification.RateSpecificationImpl(this);
		}
		
		@Override
		public RateSpecification.RateSpecificationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public RateSpecification.RateSpecificationBuilder prune() {
			if (fixedRate!=null && !fixedRate.prune().hasData()) fixedRate = null;
			if (floatingRate!=null && !floatingRate.prune().hasData()) floatingRate = null;
			if (inflationRate!=null && !inflationRate.prune().hasData()) inflationRate = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getFixedRate()!=null && getFixedRate().hasData()) return true;
			if (getFloatingRate()!=null && getFloatingRate().hasData()) return true;
			if (getInflationRate()!=null && getInflationRate().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public RateSpecification.RateSpecificationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			RateSpecification.RateSpecificationBuilder o = (RateSpecification.RateSpecificationBuilder) other;
			
			merger.mergeRosetta(getFixedRate(), o.getFixedRate(), this::setFixedRate);
			merger.mergeRosetta(getFloatingRate(), o.getFloatingRate(), this::setFloatingRate);
			merger.mergeRosetta(getInflationRate(), o.getInflationRate(), this::setInflationRate);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			RateSpecification _that = getType().cast(o);
		
			if (!Objects.equals(fixedRate, _that.getFixedRate())) return false;
			if (!Objects.equals(floatingRate, _that.getFloatingRate())) return false;
			if (!Objects.equals(inflationRate, _that.getInflationRate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (fixedRate != null ? fixedRate.hashCode() : 0);
			_result = 31 * _result + (floatingRate != null ? floatingRate.hashCode() : 0);
			_result = 31 * _result + (inflationRate != null ? inflationRate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "RateSpecificationBuilder {" +
				"fixedRate=" + this.fixedRate + ", " +
				"floatingRate=" + this.floatingRate + ", " +
				"inflationRate=" + this.inflationRate +
			'}';
		}
	}
}
