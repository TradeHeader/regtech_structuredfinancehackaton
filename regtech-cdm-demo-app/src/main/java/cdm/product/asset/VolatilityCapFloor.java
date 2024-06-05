package cdm.product.asset;

import cdm.product.asset.VolatilityCapFloor;
import cdm.product.asset.VolatilityCapFloor.VolatilityCapFloorBuilder;
import cdm.product.asset.VolatilityCapFloor.VolatilityCapFloorBuilderImpl;
import cdm.product.asset.VolatilityCapFloor.VolatilityCapFloorImpl;
import cdm.product.asset.meta.VolatilityCapFloorMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Contains volatility-based barriers. Volatility Cap needs to be specified in accordance with the ISDA 2011 Equity Derivatives Definitions.
 * @version ${project.version}
 */
@RosettaDataType(value="VolatilityCapFloor", builder=VolatilityCapFloor.VolatilityCapFloorBuilderImpl.class, version="${project.version}")
public interface VolatilityCapFloor extends RosettaModelObject {

	VolatilityCapFloorMeta metaData = new VolatilityCapFloorMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Indicates whether the volatility cap is applicable in accordance with the ISDA 2011 Equity Derivatives Definitions. Setting the element &#39;applicable&#39; to &#39;False&#39; - means No Volatility Cap and no &#39;totalVolatilityCap&#39; or &#39;volatilityCapFactor&#39; should be provided. Setting the element &#39;applicable&#39; to &#39;True&#39; - means Volatility Cap election, then &#39;totalVolatilityCap&#39; or &#39;volatilityCapFactor&#39; should be provided, otherwise it defaults to volatilityCapFactor=2.5.
	 */
	Boolean getApplicable();
	/**
	 * Volatility Cap Amount in accordance with the ISDA 2011 Equity Derivatives Definitions. This means the Volatility Cap Amount election is a number.
	 */
	BigDecimal getTotalVolatilityCap();
	/**
	 * Volatility Cap Amount in accordance with the ISDA 2011 Equity Derivatives Definitions. The Calculated VolCapAmt can be optionally provided.
	 */
	BigDecimal getVolatilityCapFactor();

	/*********************** Build Methods  ***********************/
	VolatilityCapFloor build();
	
	VolatilityCapFloor.VolatilityCapFloorBuilder toBuilder();
	
	static VolatilityCapFloor.VolatilityCapFloorBuilder builder() {
		return new VolatilityCapFloor.VolatilityCapFloorBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends VolatilityCapFloor> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends VolatilityCapFloor> getType() {
		return VolatilityCapFloor.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("applicable"), Boolean.class, getApplicable(), this);
		processor.processBasic(path.newSubPath("totalVolatilityCap"), BigDecimal.class, getTotalVolatilityCap(), this);
		processor.processBasic(path.newSubPath("volatilityCapFactor"), BigDecimal.class, getVolatilityCapFactor(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface VolatilityCapFloorBuilder extends VolatilityCapFloor, RosettaModelObjectBuilder {
		VolatilityCapFloor.VolatilityCapFloorBuilder setApplicable(Boolean applicable);
		VolatilityCapFloor.VolatilityCapFloorBuilder setTotalVolatilityCap(BigDecimal totalVolatilityCap);
		VolatilityCapFloor.VolatilityCapFloorBuilder setVolatilityCapFactor(BigDecimal volatilityCapFactor);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("applicable"), Boolean.class, getApplicable(), this);
			processor.processBasic(path.newSubPath("totalVolatilityCap"), BigDecimal.class, getTotalVolatilityCap(), this);
			processor.processBasic(path.newSubPath("volatilityCapFactor"), BigDecimal.class, getVolatilityCapFactor(), this);
		}
		

		VolatilityCapFloor.VolatilityCapFloorBuilder prune();
	}

	/*********************** Immutable Implementation of VolatilityCapFloor  ***********************/
	class VolatilityCapFloorImpl implements VolatilityCapFloor {
		private final Boolean applicable;
		private final BigDecimal totalVolatilityCap;
		private final BigDecimal volatilityCapFactor;
		
		protected VolatilityCapFloorImpl(VolatilityCapFloor.VolatilityCapFloorBuilder builder) {
			this.applicable = builder.getApplicable();
			this.totalVolatilityCap = builder.getTotalVolatilityCap();
			this.volatilityCapFactor = builder.getVolatilityCapFactor();
		}
		
		@Override
		@RosettaAttribute("applicable")
		public Boolean getApplicable() {
			return applicable;
		}
		
		@Override
		@RosettaAttribute("totalVolatilityCap")
		public BigDecimal getTotalVolatilityCap() {
			return totalVolatilityCap;
		}
		
		@Override
		@RosettaAttribute("volatilityCapFactor")
		public BigDecimal getVolatilityCapFactor() {
			return volatilityCapFactor;
		}
		
		@Override
		public VolatilityCapFloor build() {
			return this;
		}
		
		@Override
		public VolatilityCapFloor.VolatilityCapFloorBuilder toBuilder() {
			VolatilityCapFloor.VolatilityCapFloorBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(VolatilityCapFloor.VolatilityCapFloorBuilder builder) {
			ofNullable(getApplicable()).ifPresent(builder::setApplicable);
			ofNullable(getTotalVolatilityCap()).ifPresent(builder::setTotalVolatilityCap);
			ofNullable(getVolatilityCapFactor()).ifPresent(builder::setVolatilityCapFactor);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			VolatilityCapFloor _that = getType().cast(o);
		
			if (!Objects.equals(applicable, _that.getApplicable())) return false;
			if (!Objects.equals(totalVolatilityCap, _that.getTotalVolatilityCap())) return false;
			if (!Objects.equals(volatilityCapFactor, _that.getVolatilityCapFactor())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (applicable != null ? applicable.hashCode() : 0);
			_result = 31 * _result + (totalVolatilityCap != null ? totalVolatilityCap.hashCode() : 0);
			_result = 31 * _result + (volatilityCapFactor != null ? volatilityCapFactor.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "VolatilityCapFloor {" +
				"applicable=" + this.applicable + ", " +
				"totalVolatilityCap=" + this.totalVolatilityCap + ", " +
				"volatilityCapFactor=" + this.volatilityCapFactor +
			'}';
		}
	}

	/*********************** Builder Implementation of VolatilityCapFloor  ***********************/
	class VolatilityCapFloorBuilderImpl implements VolatilityCapFloor.VolatilityCapFloorBuilder {
	
		protected Boolean applicable;
		protected BigDecimal totalVolatilityCap;
		protected BigDecimal volatilityCapFactor;
	
		public VolatilityCapFloorBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("applicable")
		public Boolean getApplicable() {
			return applicable;
		}
		
		@Override
		@RosettaAttribute("totalVolatilityCap")
		public BigDecimal getTotalVolatilityCap() {
			return totalVolatilityCap;
		}
		
		@Override
		@RosettaAttribute("volatilityCapFactor")
		public BigDecimal getVolatilityCapFactor() {
			return volatilityCapFactor;
		}
		
	
		@Override
		@RosettaAttribute("applicable")
		public VolatilityCapFloor.VolatilityCapFloorBuilder setApplicable(Boolean applicable) {
			this.applicable = applicable==null?null:applicable;
			return this;
		}
		@Override
		@RosettaAttribute("totalVolatilityCap")
		public VolatilityCapFloor.VolatilityCapFloorBuilder setTotalVolatilityCap(BigDecimal totalVolatilityCap) {
			this.totalVolatilityCap = totalVolatilityCap==null?null:totalVolatilityCap;
			return this;
		}
		@Override
		@RosettaAttribute("volatilityCapFactor")
		public VolatilityCapFloor.VolatilityCapFloorBuilder setVolatilityCapFactor(BigDecimal volatilityCapFactor) {
			this.volatilityCapFactor = volatilityCapFactor==null?null:volatilityCapFactor;
			return this;
		}
		
		@Override
		public VolatilityCapFloor build() {
			return new VolatilityCapFloor.VolatilityCapFloorImpl(this);
		}
		
		@Override
		public VolatilityCapFloor.VolatilityCapFloorBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public VolatilityCapFloor.VolatilityCapFloorBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getApplicable()!=null) return true;
			if (getTotalVolatilityCap()!=null) return true;
			if (getVolatilityCapFactor()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public VolatilityCapFloor.VolatilityCapFloorBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			VolatilityCapFloor.VolatilityCapFloorBuilder o = (VolatilityCapFloor.VolatilityCapFloorBuilder) other;
			
			
			merger.mergeBasic(getApplicable(), o.getApplicable(), this::setApplicable);
			merger.mergeBasic(getTotalVolatilityCap(), o.getTotalVolatilityCap(), this::setTotalVolatilityCap);
			merger.mergeBasic(getVolatilityCapFactor(), o.getVolatilityCapFactor(), this::setVolatilityCapFactor);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			VolatilityCapFloor _that = getType().cast(o);
		
			if (!Objects.equals(applicable, _that.getApplicable())) return false;
			if (!Objects.equals(totalVolatilityCap, _that.getTotalVolatilityCap())) return false;
			if (!Objects.equals(volatilityCapFactor, _that.getVolatilityCapFactor())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (applicable != null ? applicable.hashCode() : 0);
			_result = 31 * _result + (totalVolatilityCap != null ? totalVolatilityCap.hashCode() : 0);
			_result = 31 * _result + (volatilityCapFactor != null ? volatilityCapFactor.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "VolatilityCapFloorBuilder {" +
				"applicable=" + this.applicable + ", " +
				"totalVolatilityCap=" + this.totalVolatilityCap + ", " +
				"volatilityCapFactor=" + this.volatilityCapFactor +
			'}';
		}
	}
}
