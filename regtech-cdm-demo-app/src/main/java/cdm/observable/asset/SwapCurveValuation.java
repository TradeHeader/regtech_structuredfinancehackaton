package cdm.observable.asset;

import cdm.base.datetime.Period;
import cdm.base.staticdata.asset.rates.FloatingRateIndexEnum;
import cdm.observable.asset.QuotationSideEnum;
import cdm.observable.asset.SwapCurveValuation;
import cdm.observable.asset.SwapCurveValuation.SwapCurveValuationBuilder;
import cdm.observable.asset.SwapCurveValuation.SwapCurveValuationBuilderImpl;
import cdm.observable.asset.SwapCurveValuation.SwapCurveValuationImpl;
import cdm.observable.asset.meta.SwapCurveValuationMeta;
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
 * A class to specify a valuation swap curve, which is used as part of the strike construct for the bond and convertible bond options.
 * @version ${project.version}
 */
@RosettaDataType(value="SwapCurveValuation", builder=SwapCurveValuation.SwapCurveValuationBuilderImpl.class, version="${project.version}")
public interface SwapCurveValuation extends RosettaModelObject {

	SwapCurveValuationMeta metaData = new SwapCurveValuationMeta();

	/*********************** Getter Methods  ***********************/
	FloatingRateIndexEnum getFloatingRateIndex();
	/**
	 * The ISDA Designated Maturity, i.e. the tenor of the floating rate.
	 */
	Period getIndexTenor();
	/**
	 * Spread in basis points over the floating rate index.
	 */
	BigDecimal getSpread();
	/**
	 * The side (bid/mid/ask) of the measure.
	 */
	QuotationSideEnum getSide();

	/*********************** Build Methods  ***********************/
	SwapCurveValuation build();
	
	SwapCurveValuation.SwapCurveValuationBuilder toBuilder();
	
	static SwapCurveValuation.SwapCurveValuationBuilder builder() {
		return new SwapCurveValuation.SwapCurveValuationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends SwapCurveValuation> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends SwapCurveValuation> getType() {
		return SwapCurveValuation.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("floatingRateIndex"), FloatingRateIndexEnum.class, getFloatingRateIndex(), this);
		processRosetta(path.newSubPath("indexTenor"), processor, Period.class, getIndexTenor());
		processor.processBasic(path.newSubPath("spread"), BigDecimal.class, getSpread(), this);
		processor.processBasic(path.newSubPath("side"), QuotationSideEnum.class, getSide(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface SwapCurveValuationBuilder extends SwapCurveValuation, RosettaModelObjectBuilder {
		Period.PeriodBuilder getOrCreateIndexTenor();
		Period.PeriodBuilder getIndexTenor();
		SwapCurveValuation.SwapCurveValuationBuilder setFloatingRateIndex(FloatingRateIndexEnum floatingRateIndex);
		SwapCurveValuation.SwapCurveValuationBuilder setIndexTenor(Period indexTenor);
		SwapCurveValuation.SwapCurveValuationBuilder setSpread(BigDecimal spread);
		SwapCurveValuation.SwapCurveValuationBuilder setSide(QuotationSideEnum side);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("floatingRateIndex"), FloatingRateIndexEnum.class, getFloatingRateIndex(), this);
			processRosetta(path.newSubPath("indexTenor"), processor, Period.PeriodBuilder.class, getIndexTenor());
			processor.processBasic(path.newSubPath("spread"), BigDecimal.class, getSpread(), this);
			processor.processBasic(path.newSubPath("side"), QuotationSideEnum.class, getSide(), this);
		}
		

		SwapCurveValuation.SwapCurveValuationBuilder prune();
	}

	/*********************** Immutable Implementation of SwapCurveValuation  ***********************/
	class SwapCurveValuationImpl implements SwapCurveValuation {
		private final FloatingRateIndexEnum floatingRateIndex;
		private final Period indexTenor;
		private final BigDecimal spread;
		private final QuotationSideEnum side;
		
		protected SwapCurveValuationImpl(SwapCurveValuation.SwapCurveValuationBuilder builder) {
			this.floatingRateIndex = builder.getFloatingRateIndex();
			this.indexTenor = ofNullable(builder.getIndexTenor()).map(f->f.build()).orElse(null);
			this.spread = builder.getSpread();
			this.side = builder.getSide();
		}
		
		@Override
		@RosettaAttribute("floatingRateIndex")
		public FloatingRateIndexEnum getFloatingRateIndex() {
			return floatingRateIndex;
		}
		
		@Override
		@RosettaAttribute("indexTenor")
		public Period getIndexTenor() {
			return indexTenor;
		}
		
		@Override
		@RosettaAttribute("spread")
		public BigDecimal getSpread() {
			return spread;
		}
		
		@Override
		@RosettaAttribute("side")
		public QuotationSideEnum getSide() {
			return side;
		}
		
		@Override
		public SwapCurveValuation build() {
			return this;
		}
		
		@Override
		public SwapCurveValuation.SwapCurveValuationBuilder toBuilder() {
			SwapCurveValuation.SwapCurveValuationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(SwapCurveValuation.SwapCurveValuationBuilder builder) {
			ofNullable(getFloatingRateIndex()).ifPresent(builder::setFloatingRateIndex);
			ofNullable(getIndexTenor()).ifPresent(builder::setIndexTenor);
			ofNullable(getSpread()).ifPresent(builder::setSpread);
			ofNullable(getSide()).ifPresent(builder::setSide);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SwapCurveValuation _that = getType().cast(o);
		
			if (!Objects.equals(floatingRateIndex, _that.getFloatingRateIndex())) return false;
			if (!Objects.equals(indexTenor, _that.getIndexTenor())) return false;
			if (!Objects.equals(spread, _that.getSpread())) return false;
			if (!Objects.equals(side, _that.getSide())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (floatingRateIndex != null ? floatingRateIndex.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (indexTenor != null ? indexTenor.hashCode() : 0);
			_result = 31 * _result + (spread != null ? spread.hashCode() : 0);
			_result = 31 * _result + (side != null ? side.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SwapCurveValuation {" +
				"floatingRateIndex=" + this.floatingRateIndex + ", " +
				"indexTenor=" + this.indexTenor + ", " +
				"spread=" + this.spread + ", " +
				"side=" + this.side +
			'}';
		}
	}

	/*********************** Builder Implementation of SwapCurveValuation  ***********************/
	class SwapCurveValuationBuilderImpl implements SwapCurveValuation.SwapCurveValuationBuilder {
	
		protected FloatingRateIndexEnum floatingRateIndex;
		protected Period.PeriodBuilder indexTenor;
		protected BigDecimal spread;
		protected QuotationSideEnum side;
	
		public SwapCurveValuationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("floatingRateIndex")
		public FloatingRateIndexEnum getFloatingRateIndex() {
			return floatingRateIndex;
		}
		
		@Override
		@RosettaAttribute("indexTenor")
		public Period.PeriodBuilder getIndexTenor() {
			return indexTenor;
		}
		
		@Override
		public Period.PeriodBuilder getOrCreateIndexTenor() {
			Period.PeriodBuilder result;
			if (indexTenor!=null) {
				result = indexTenor;
			}
			else {
				result = indexTenor = Period.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("spread")
		public BigDecimal getSpread() {
			return spread;
		}
		
		@Override
		@RosettaAttribute("side")
		public QuotationSideEnum getSide() {
			return side;
		}
		
	
		@Override
		@RosettaAttribute("floatingRateIndex")
		public SwapCurveValuation.SwapCurveValuationBuilder setFloatingRateIndex(FloatingRateIndexEnum floatingRateIndex) {
			this.floatingRateIndex = floatingRateIndex==null?null:floatingRateIndex;
			return this;
		}
		@Override
		@RosettaAttribute("indexTenor")
		public SwapCurveValuation.SwapCurveValuationBuilder setIndexTenor(Period indexTenor) {
			this.indexTenor = indexTenor==null?null:indexTenor.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("spread")
		public SwapCurveValuation.SwapCurveValuationBuilder setSpread(BigDecimal spread) {
			this.spread = spread==null?null:spread;
			return this;
		}
		@Override
		@RosettaAttribute("side")
		public SwapCurveValuation.SwapCurveValuationBuilder setSide(QuotationSideEnum side) {
			this.side = side==null?null:side;
			return this;
		}
		
		@Override
		public SwapCurveValuation build() {
			return new SwapCurveValuation.SwapCurveValuationImpl(this);
		}
		
		@Override
		public SwapCurveValuation.SwapCurveValuationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SwapCurveValuation.SwapCurveValuationBuilder prune() {
			if (indexTenor!=null && !indexTenor.prune().hasData()) indexTenor = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getFloatingRateIndex()!=null) return true;
			if (getIndexTenor()!=null && getIndexTenor().hasData()) return true;
			if (getSpread()!=null) return true;
			if (getSide()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SwapCurveValuation.SwapCurveValuationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			SwapCurveValuation.SwapCurveValuationBuilder o = (SwapCurveValuation.SwapCurveValuationBuilder) other;
			
			merger.mergeRosetta(getIndexTenor(), o.getIndexTenor(), this::setIndexTenor);
			
			merger.mergeBasic(getFloatingRateIndex(), o.getFloatingRateIndex(), this::setFloatingRateIndex);
			merger.mergeBasic(getSpread(), o.getSpread(), this::setSpread);
			merger.mergeBasic(getSide(), o.getSide(), this::setSide);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SwapCurveValuation _that = getType().cast(o);
		
			if (!Objects.equals(floatingRateIndex, _that.getFloatingRateIndex())) return false;
			if (!Objects.equals(indexTenor, _that.getIndexTenor())) return false;
			if (!Objects.equals(spread, _that.getSpread())) return false;
			if (!Objects.equals(side, _that.getSide())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (floatingRateIndex != null ? floatingRateIndex.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (indexTenor != null ? indexTenor.hashCode() : 0);
			_result = 31 * _result + (spread != null ? spread.hashCode() : 0);
			_result = 31 * _result + (side != null ? side.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SwapCurveValuationBuilder {" +
				"floatingRateIndex=" + this.floatingRateIndex + ", " +
				"indexTenor=" + this.indexTenor + ", " +
				"spread=" + this.spread + ", " +
				"side=" + this.side +
			'}';
		}
	}
}
