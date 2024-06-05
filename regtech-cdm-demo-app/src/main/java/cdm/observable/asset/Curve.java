package cdm.observable.asset;

import cdm.observable.asset.CommodityReferencePriceEnum;
import cdm.observable.asset.Curve;
import cdm.observable.asset.Curve.CurveBuilder;
import cdm.observable.asset.Curve.CurveBuilderImpl;
import cdm.observable.asset.Curve.CurveImpl;
import cdm.observable.asset.InterestRateCurve;
import cdm.observable.asset.meta.CurveMeta;
import cdm.observable.asset.metafields.FieldWithMetaCommodityReferencePriceEnum;
import cdm.observable.asset.metafields.FieldWithMetaCommodityReferencePriceEnum.FieldWithMetaCommodityReferencePriceEnumBuilder;
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
 * @version ${project.version}
 */
@RosettaDataType(value="Curve", builder=Curve.CurveBuilderImpl.class, version="${project.version}")
public interface Curve extends RosettaModelObject {

	CurveMeta metaData = new CurveMeta();

	/*********************** Getter Methods  ***********************/
	InterestRateCurve getInterestRateCurve();
	FieldWithMetaCommodityReferencePriceEnum getCommodityCurve();

	/*********************** Build Methods  ***********************/
	Curve build();
	
	Curve.CurveBuilder toBuilder();
	
	static Curve.CurveBuilder builder() {
		return new Curve.CurveBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Curve> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Curve> getType() {
		return Curve.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("interestRateCurve"), processor, InterestRateCurve.class, getInterestRateCurve());
		processRosetta(path.newSubPath("commodityCurve"), processor, FieldWithMetaCommodityReferencePriceEnum.class, getCommodityCurve());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CurveBuilder extends Curve, RosettaModelObjectBuilder {
		InterestRateCurve.InterestRateCurveBuilder getOrCreateInterestRateCurve();
		InterestRateCurve.InterestRateCurveBuilder getInterestRateCurve();
		FieldWithMetaCommodityReferencePriceEnum.FieldWithMetaCommodityReferencePriceEnumBuilder getOrCreateCommodityCurve();
		FieldWithMetaCommodityReferencePriceEnum.FieldWithMetaCommodityReferencePriceEnumBuilder getCommodityCurve();
		Curve.CurveBuilder setInterestRateCurve(InterestRateCurve interestRateCurve);
		Curve.CurveBuilder setCommodityCurve(FieldWithMetaCommodityReferencePriceEnum commodityCurve0);
		Curve.CurveBuilder setCommodityCurveValue(CommodityReferencePriceEnum commodityCurve1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("interestRateCurve"), processor, InterestRateCurve.InterestRateCurveBuilder.class, getInterestRateCurve());
			processRosetta(path.newSubPath("commodityCurve"), processor, FieldWithMetaCommodityReferencePriceEnum.FieldWithMetaCommodityReferencePriceEnumBuilder.class, getCommodityCurve());
		}
		

		Curve.CurveBuilder prune();
	}

	/*********************** Immutable Implementation of Curve  ***********************/
	class CurveImpl implements Curve {
		private final InterestRateCurve interestRateCurve;
		private final FieldWithMetaCommodityReferencePriceEnum commodityCurve;
		
		protected CurveImpl(Curve.CurveBuilder builder) {
			this.interestRateCurve = ofNullable(builder.getInterestRateCurve()).map(f->f.build()).orElse(null);
			this.commodityCurve = ofNullable(builder.getCommodityCurve()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("interestRateCurve")
		public InterestRateCurve getInterestRateCurve() {
			return interestRateCurve;
		}
		
		@Override
		@RosettaAttribute("commodityCurve")
		public FieldWithMetaCommodityReferencePriceEnum getCommodityCurve() {
			return commodityCurve;
		}
		
		@Override
		public Curve build() {
			return this;
		}
		
		@Override
		public Curve.CurveBuilder toBuilder() {
			Curve.CurveBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Curve.CurveBuilder builder) {
			ofNullable(getInterestRateCurve()).ifPresent(builder::setInterestRateCurve);
			ofNullable(getCommodityCurve()).ifPresent(builder::setCommodityCurve);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Curve _that = getType().cast(o);
		
			if (!Objects.equals(interestRateCurve, _that.getInterestRateCurve())) return false;
			if (!Objects.equals(commodityCurve, _that.getCommodityCurve())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (interestRateCurve != null ? interestRateCurve.hashCode() : 0);
			_result = 31 * _result + (commodityCurve != null ? commodityCurve.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Curve {" +
				"interestRateCurve=" + this.interestRateCurve + ", " +
				"commodityCurve=" + this.commodityCurve +
			'}';
		}
	}

	/*********************** Builder Implementation of Curve  ***********************/
	class CurveBuilderImpl implements Curve.CurveBuilder {
	
		protected InterestRateCurve.InterestRateCurveBuilder interestRateCurve;
		protected FieldWithMetaCommodityReferencePriceEnum.FieldWithMetaCommodityReferencePriceEnumBuilder commodityCurve;
	
		public CurveBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("interestRateCurve")
		public InterestRateCurve.InterestRateCurveBuilder getInterestRateCurve() {
			return interestRateCurve;
		}
		
		@Override
		public InterestRateCurve.InterestRateCurveBuilder getOrCreateInterestRateCurve() {
			InterestRateCurve.InterestRateCurveBuilder result;
			if (interestRateCurve!=null) {
				result = interestRateCurve;
			}
			else {
				result = interestRateCurve = InterestRateCurve.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("commodityCurve")
		public FieldWithMetaCommodityReferencePriceEnum.FieldWithMetaCommodityReferencePriceEnumBuilder getCommodityCurve() {
			return commodityCurve;
		}
		
		@Override
		public FieldWithMetaCommodityReferencePriceEnum.FieldWithMetaCommodityReferencePriceEnumBuilder getOrCreateCommodityCurve() {
			FieldWithMetaCommodityReferencePriceEnum.FieldWithMetaCommodityReferencePriceEnumBuilder result;
			if (commodityCurve!=null) {
				result = commodityCurve;
			}
			else {
				result = commodityCurve = FieldWithMetaCommodityReferencePriceEnum.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("interestRateCurve")
		public Curve.CurveBuilder setInterestRateCurve(InterestRateCurve interestRateCurve) {
			this.interestRateCurve = interestRateCurve==null?null:interestRateCurve.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("commodityCurve")
		public Curve.CurveBuilder setCommodityCurve(FieldWithMetaCommodityReferencePriceEnum commodityCurve) {
			this.commodityCurve = commodityCurve==null?null:commodityCurve.toBuilder();
			return this;
		}
		@Override
		public Curve.CurveBuilder setCommodityCurveValue(CommodityReferencePriceEnum commodityCurve) {
			this.getOrCreateCommodityCurve().setValue(commodityCurve);
			return this;
		}
		
		@Override
		public Curve build() {
			return new Curve.CurveImpl(this);
		}
		
		@Override
		public Curve.CurveBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Curve.CurveBuilder prune() {
			if (interestRateCurve!=null && !interestRateCurve.prune().hasData()) interestRateCurve = null;
			if (commodityCurve!=null && !commodityCurve.prune().hasData()) commodityCurve = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getInterestRateCurve()!=null && getInterestRateCurve().hasData()) return true;
			if (getCommodityCurve()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Curve.CurveBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Curve.CurveBuilder o = (Curve.CurveBuilder) other;
			
			merger.mergeRosetta(getInterestRateCurve(), o.getInterestRateCurve(), this::setInterestRateCurve);
			merger.mergeRosetta(getCommodityCurve(), o.getCommodityCurve(), this::setCommodityCurve);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Curve _that = getType().cast(o);
		
			if (!Objects.equals(interestRateCurve, _that.getInterestRateCurve())) return false;
			if (!Objects.equals(commodityCurve, _that.getCommodityCurve())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (interestRateCurve != null ? interestRateCurve.hashCode() : 0);
			_result = 31 * _result + (commodityCurve != null ? commodityCurve.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CurveBuilder {" +
				"interestRateCurve=" + this.interestRateCurve + ", " +
				"commodityCurve=" + this.commodityCurve +
			'}';
		}
	}
}
