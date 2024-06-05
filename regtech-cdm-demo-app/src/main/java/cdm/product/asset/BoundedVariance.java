package cdm.product.asset;

import cdm.product.asset.BoundedVariance;
import cdm.product.asset.BoundedVariance.BoundedVarianceBuilder;
import cdm.product.asset.BoundedVariance.BoundedVarianceBuilderImpl;
import cdm.product.asset.BoundedVariance.BoundedVarianceImpl;
import cdm.product.asset.RealisedVarianceMethodEnum;
import cdm.product.asset.meta.BoundedVarianceMeta;
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
 * @version ${project.version}
 */
@RosettaDataType(value="BoundedVariance", builder=BoundedVariance.BoundedVarianceBuilderImpl.class, version="${project.version}")
public interface BoundedVariance extends RosettaModelObject {

	BoundedVarianceMeta metaData = new BoundedVarianceMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The contract specifies which price must satisfy the boundary condition.
	 */
	RealisedVarianceMethodEnum getRealisedVarianceMethod();
	/**
	 * The contract specifies whether the notional should be scaled by the Number of Days in Range divided by the Expected N. The number of Days in Ranges refers to the number of returns that contribute to the realized volatility.
	 */
	Boolean getDaysInRangeAdjustment();
	/**
	 * All observations above this price level will be excluded from the variance calculation.
	 */
	BigDecimal getUpperBarrier();
	/**
	 * All observations below this price level will be excluded from the variance calculation.
	 */
	BigDecimal getLowerBarrier();

	/*********************** Build Methods  ***********************/
	BoundedVariance build();
	
	BoundedVariance.BoundedVarianceBuilder toBuilder();
	
	static BoundedVariance.BoundedVarianceBuilder builder() {
		return new BoundedVariance.BoundedVarianceBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends BoundedVariance> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends BoundedVariance> getType() {
		return BoundedVariance.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("realisedVarianceMethod"), RealisedVarianceMethodEnum.class, getRealisedVarianceMethod(), this);
		processor.processBasic(path.newSubPath("daysInRangeAdjustment"), Boolean.class, getDaysInRangeAdjustment(), this);
		processor.processBasic(path.newSubPath("upperBarrier"), BigDecimal.class, getUpperBarrier(), this);
		processor.processBasic(path.newSubPath("lowerBarrier"), BigDecimal.class, getLowerBarrier(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface BoundedVarianceBuilder extends BoundedVariance, RosettaModelObjectBuilder {
		BoundedVariance.BoundedVarianceBuilder setRealisedVarianceMethod(RealisedVarianceMethodEnum realisedVarianceMethod);
		BoundedVariance.BoundedVarianceBuilder setDaysInRangeAdjustment(Boolean daysInRangeAdjustment);
		BoundedVariance.BoundedVarianceBuilder setUpperBarrier(BigDecimal upperBarrier);
		BoundedVariance.BoundedVarianceBuilder setLowerBarrier(BigDecimal lowerBarrier);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("realisedVarianceMethod"), RealisedVarianceMethodEnum.class, getRealisedVarianceMethod(), this);
			processor.processBasic(path.newSubPath("daysInRangeAdjustment"), Boolean.class, getDaysInRangeAdjustment(), this);
			processor.processBasic(path.newSubPath("upperBarrier"), BigDecimal.class, getUpperBarrier(), this);
			processor.processBasic(path.newSubPath("lowerBarrier"), BigDecimal.class, getLowerBarrier(), this);
		}
		

		BoundedVariance.BoundedVarianceBuilder prune();
	}

	/*********************** Immutable Implementation of BoundedVariance  ***********************/
	class BoundedVarianceImpl implements BoundedVariance {
		private final RealisedVarianceMethodEnum realisedVarianceMethod;
		private final Boolean daysInRangeAdjustment;
		private final BigDecimal upperBarrier;
		private final BigDecimal lowerBarrier;
		
		protected BoundedVarianceImpl(BoundedVariance.BoundedVarianceBuilder builder) {
			this.realisedVarianceMethod = builder.getRealisedVarianceMethod();
			this.daysInRangeAdjustment = builder.getDaysInRangeAdjustment();
			this.upperBarrier = builder.getUpperBarrier();
			this.lowerBarrier = builder.getLowerBarrier();
		}
		
		@Override
		@RosettaAttribute("realisedVarianceMethod")
		public RealisedVarianceMethodEnum getRealisedVarianceMethod() {
			return realisedVarianceMethod;
		}
		
		@Override
		@RosettaAttribute("daysInRangeAdjustment")
		public Boolean getDaysInRangeAdjustment() {
			return daysInRangeAdjustment;
		}
		
		@Override
		@RosettaAttribute("upperBarrier")
		public BigDecimal getUpperBarrier() {
			return upperBarrier;
		}
		
		@Override
		@RosettaAttribute("lowerBarrier")
		public BigDecimal getLowerBarrier() {
			return lowerBarrier;
		}
		
		@Override
		public BoundedVariance build() {
			return this;
		}
		
		@Override
		public BoundedVariance.BoundedVarianceBuilder toBuilder() {
			BoundedVariance.BoundedVarianceBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(BoundedVariance.BoundedVarianceBuilder builder) {
			ofNullable(getRealisedVarianceMethod()).ifPresent(builder::setRealisedVarianceMethod);
			ofNullable(getDaysInRangeAdjustment()).ifPresent(builder::setDaysInRangeAdjustment);
			ofNullable(getUpperBarrier()).ifPresent(builder::setUpperBarrier);
			ofNullable(getLowerBarrier()).ifPresent(builder::setLowerBarrier);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			BoundedVariance _that = getType().cast(o);
		
			if (!Objects.equals(realisedVarianceMethod, _that.getRealisedVarianceMethod())) return false;
			if (!Objects.equals(daysInRangeAdjustment, _that.getDaysInRangeAdjustment())) return false;
			if (!Objects.equals(upperBarrier, _that.getUpperBarrier())) return false;
			if (!Objects.equals(lowerBarrier, _that.getLowerBarrier())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (realisedVarianceMethod != null ? realisedVarianceMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (daysInRangeAdjustment != null ? daysInRangeAdjustment.hashCode() : 0);
			_result = 31 * _result + (upperBarrier != null ? upperBarrier.hashCode() : 0);
			_result = 31 * _result + (lowerBarrier != null ? lowerBarrier.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BoundedVariance {" +
				"realisedVarianceMethod=" + this.realisedVarianceMethod + ", " +
				"daysInRangeAdjustment=" + this.daysInRangeAdjustment + ", " +
				"upperBarrier=" + this.upperBarrier + ", " +
				"lowerBarrier=" + this.lowerBarrier +
			'}';
		}
	}

	/*********************** Builder Implementation of BoundedVariance  ***********************/
	class BoundedVarianceBuilderImpl implements BoundedVariance.BoundedVarianceBuilder {
	
		protected RealisedVarianceMethodEnum realisedVarianceMethod;
		protected Boolean daysInRangeAdjustment;
		protected BigDecimal upperBarrier;
		protected BigDecimal lowerBarrier;
	
		public BoundedVarianceBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("realisedVarianceMethod")
		public RealisedVarianceMethodEnum getRealisedVarianceMethod() {
			return realisedVarianceMethod;
		}
		
		@Override
		@RosettaAttribute("daysInRangeAdjustment")
		public Boolean getDaysInRangeAdjustment() {
			return daysInRangeAdjustment;
		}
		
		@Override
		@RosettaAttribute("upperBarrier")
		public BigDecimal getUpperBarrier() {
			return upperBarrier;
		}
		
		@Override
		@RosettaAttribute("lowerBarrier")
		public BigDecimal getLowerBarrier() {
			return lowerBarrier;
		}
		
	
		@Override
		@RosettaAttribute("realisedVarianceMethod")
		public BoundedVariance.BoundedVarianceBuilder setRealisedVarianceMethod(RealisedVarianceMethodEnum realisedVarianceMethod) {
			this.realisedVarianceMethod = realisedVarianceMethod==null?null:realisedVarianceMethod;
			return this;
		}
		@Override
		@RosettaAttribute("daysInRangeAdjustment")
		public BoundedVariance.BoundedVarianceBuilder setDaysInRangeAdjustment(Boolean daysInRangeAdjustment) {
			this.daysInRangeAdjustment = daysInRangeAdjustment==null?null:daysInRangeAdjustment;
			return this;
		}
		@Override
		@RosettaAttribute("upperBarrier")
		public BoundedVariance.BoundedVarianceBuilder setUpperBarrier(BigDecimal upperBarrier) {
			this.upperBarrier = upperBarrier==null?null:upperBarrier;
			return this;
		}
		@Override
		@RosettaAttribute("lowerBarrier")
		public BoundedVariance.BoundedVarianceBuilder setLowerBarrier(BigDecimal lowerBarrier) {
			this.lowerBarrier = lowerBarrier==null?null:lowerBarrier;
			return this;
		}
		
		@Override
		public BoundedVariance build() {
			return new BoundedVariance.BoundedVarianceImpl(this);
		}
		
		@Override
		public BoundedVariance.BoundedVarianceBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BoundedVariance.BoundedVarianceBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getRealisedVarianceMethod()!=null) return true;
			if (getDaysInRangeAdjustment()!=null) return true;
			if (getUpperBarrier()!=null) return true;
			if (getLowerBarrier()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BoundedVariance.BoundedVarianceBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			BoundedVariance.BoundedVarianceBuilder o = (BoundedVariance.BoundedVarianceBuilder) other;
			
			
			merger.mergeBasic(getRealisedVarianceMethod(), o.getRealisedVarianceMethod(), this::setRealisedVarianceMethod);
			merger.mergeBasic(getDaysInRangeAdjustment(), o.getDaysInRangeAdjustment(), this::setDaysInRangeAdjustment);
			merger.mergeBasic(getUpperBarrier(), o.getUpperBarrier(), this::setUpperBarrier);
			merger.mergeBasic(getLowerBarrier(), o.getLowerBarrier(), this::setLowerBarrier);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			BoundedVariance _that = getType().cast(o);
		
			if (!Objects.equals(realisedVarianceMethod, _that.getRealisedVarianceMethod())) return false;
			if (!Objects.equals(daysInRangeAdjustment, _that.getDaysInRangeAdjustment())) return false;
			if (!Objects.equals(upperBarrier, _that.getUpperBarrier())) return false;
			if (!Objects.equals(lowerBarrier, _that.getLowerBarrier())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (realisedVarianceMethod != null ? realisedVarianceMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (daysInRangeAdjustment != null ? daysInRangeAdjustment.hashCode() : 0);
			_result = 31 * _result + (upperBarrier != null ? upperBarrier.hashCode() : 0);
			_result = 31 * _result + (lowerBarrier != null ? lowerBarrier.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BoundedVarianceBuilder {" +
				"realisedVarianceMethod=" + this.realisedVarianceMethod + ", " +
				"daysInRangeAdjustment=" + this.daysInRangeAdjustment + ", " +
				"upperBarrier=" + this.upperBarrier + ", " +
				"lowerBarrier=" + this.lowerBarrier +
			'}';
		}
	}
}
