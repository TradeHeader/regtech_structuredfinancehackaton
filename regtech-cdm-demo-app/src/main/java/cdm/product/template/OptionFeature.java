package cdm.product.template;

import cdm.product.template.AveragingCalculation;
import cdm.product.template.Barrier;
import cdm.product.template.FxFeature;
import cdm.product.template.Knock;
import cdm.product.template.OptionFeature;
import cdm.product.template.OptionFeature.OptionFeatureBuilder;
import cdm.product.template.OptionFeature.OptionFeatureBuilderImpl;
import cdm.product.template.OptionFeature.OptionFeatureImpl;
import cdm.product.template.PassThrough;
import cdm.product.template.StrategyFeature;
import cdm.product.template.meta.OptionFeatureMeta;
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
 * Defines additional optional features that can be included in an option contract.
 * @version ${project.version}
 */
@RosettaDataType(value="OptionFeature", builder=OptionFeature.OptionFeatureBuilderImpl.class, version="${project.version}")
public interface OptionFeature extends RosettaModelObject {

	OptionFeatureMeta metaData = new OptionFeatureMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Describes a quanto or composite FX feature.
	 */
	List<? extends FxFeature> getFxFeature();
	/**
	 * Defines a simple strategy feature.
	 */
	StrategyFeature getStrategyFeature();
	/**
	 * Defines an option feature in which an average market observation price is determined on valuation and compared to the strike to determine a settlement amount.
	 */
	AveragingCalculation getAveragingFeature();
	/**
	 * Specifies a barrier feature.
	 */
	Barrier getBarrier();
	/**
	 * Specifies a knock in or knock out feature.
	 */
	Knock getKnock();
	/**
	 * Specifies the rules for pass-through payments from the underlier, such as dividends.
	 */
	PassThrough getPassThrough();

	/*********************** Build Methods  ***********************/
	OptionFeature build();
	
	OptionFeature.OptionFeatureBuilder toBuilder();
	
	static OptionFeature.OptionFeatureBuilder builder() {
		return new OptionFeature.OptionFeatureBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends OptionFeature> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends OptionFeature> getType() {
		return OptionFeature.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("fxFeature"), processor, FxFeature.class, getFxFeature());
		processRosetta(path.newSubPath("strategyFeature"), processor, StrategyFeature.class, getStrategyFeature());
		processRosetta(path.newSubPath("averagingFeature"), processor, AveragingCalculation.class, getAveragingFeature());
		processRosetta(path.newSubPath("barrier"), processor, Barrier.class, getBarrier());
		processRosetta(path.newSubPath("knock"), processor, Knock.class, getKnock());
		processRosetta(path.newSubPath("passThrough"), processor, PassThrough.class, getPassThrough());
	}
	

	/*********************** Builder Interface  ***********************/
	interface OptionFeatureBuilder extends OptionFeature, RosettaModelObjectBuilder {
		FxFeature.FxFeatureBuilder getOrCreateFxFeature(int _index);
		List<? extends FxFeature.FxFeatureBuilder> getFxFeature();
		StrategyFeature.StrategyFeatureBuilder getOrCreateStrategyFeature();
		StrategyFeature.StrategyFeatureBuilder getStrategyFeature();
		AveragingCalculation.AveragingCalculationBuilder getOrCreateAveragingFeature();
		AveragingCalculation.AveragingCalculationBuilder getAveragingFeature();
		Barrier.BarrierBuilder getOrCreateBarrier();
		Barrier.BarrierBuilder getBarrier();
		Knock.KnockBuilder getOrCreateKnock();
		Knock.KnockBuilder getKnock();
		PassThrough.PassThroughBuilder getOrCreatePassThrough();
		PassThrough.PassThroughBuilder getPassThrough();
		OptionFeature.OptionFeatureBuilder addFxFeature(FxFeature fxFeature0);
		OptionFeature.OptionFeatureBuilder addFxFeature(FxFeature fxFeature1, int _idx);
		OptionFeature.OptionFeatureBuilder addFxFeature(List<? extends FxFeature> fxFeature2);
		OptionFeature.OptionFeatureBuilder setFxFeature(List<? extends FxFeature> fxFeature3);
		OptionFeature.OptionFeatureBuilder setStrategyFeature(StrategyFeature strategyFeature);
		OptionFeature.OptionFeatureBuilder setAveragingFeature(AveragingCalculation averagingFeature);
		OptionFeature.OptionFeatureBuilder setBarrier(Barrier barrier);
		OptionFeature.OptionFeatureBuilder setKnock(Knock knock);
		OptionFeature.OptionFeatureBuilder setPassThrough(PassThrough passThrough);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("fxFeature"), processor, FxFeature.FxFeatureBuilder.class, getFxFeature());
			processRosetta(path.newSubPath("strategyFeature"), processor, StrategyFeature.StrategyFeatureBuilder.class, getStrategyFeature());
			processRosetta(path.newSubPath("averagingFeature"), processor, AveragingCalculation.AveragingCalculationBuilder.class, getAveragingFeature());
			processRosetta(path.newSubPath("barrier"), processor, Barrier.BarrierBuilder.class, getBarrier());
			processRosetta(path.newSubPath("knock"), processor, Knock.KnockBuilder.class, getKnock());
			processRosetta(path.newSubPath("passThrough"), processor, PassThrough.PassThroughBuilder.class, getPassThrough());
		}
		

		OptionFeature.OptionFeatureBuilder prune();
	}

	/*********************** Immutable Implementation of OptionFeature  ***********************/
	class OptionFeatureImpl implements OptionFeature {
		private final List<? extends FxFeature> fxFeature;
		private final StrategyFeature strategyFeature;
		private final AveragingCalculation averagingFeature;
		private final Barrier barrier;
		private final Knock knock;
		private final PassThrough passThrough;
		
		protected OptionFeatureImpl(OptionFeature.OptionFeatureBuilder builder) {
			this.fxFeature = ofNullable(builder.getFxFeature()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.strategyFeature = ofNullable(builder.getStrategyFeature()).map(f->f.build()).orElse(null);
			this.averagingFeature = ofNullable(builder.getAveragingFeature()).map(f->f.build()).orElse(null);
			this.barrier = ofNullable(builder.getBarrier()).map(f->f.build()).orElse(null);
			this.knock = ofNullable(builder.getKnock()).map(f->f.build()).orElse(null);
			this.passThrough = ofNullable(builder.getPassThrough()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("fxFeature")
		public List<? extends FxFeature> getFxFeature() {
			return fxFeature;
		}
		
		@Override
		@RosettaAttribute("strategyFeature")
		public StrategyFeature getStrategyFeature() {
			return strategyFeature;
		}
		
		@Override
		@RosettaAttribute("averagingFeature")
		public AveragingCalculation getAveragingFeature() {
			return averagingFeature;
		}
		
		@Override
		@RosettaAttribute("barrier")
		public Barrier getBarrier() {
			return barrier;
		}
		
		@Override
		@RosettaAttribute("knock")
		public Knock getKnock() {
			return knock;
		}
		
		@Override
		@RosettaAttribute("passThrough")
		public PassThrough getPassThrough() {
			return passThrough;
		}
		
		@Override
		public OptionFeature build() {
			return this;
		}
		
		@Override
		public OptionFeature.OptionFeatureBuilder toBuilder() {
			OptionFeature.OptionFeatureBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(OptionFeature.OptionFeatureBuilder builder) {
			ofNullable(getFxFeature()).ifPresent(builder::setFxFeature);
			ofNullable(getStrategyFeature()).ifPresent(builder::setStrategyFeature);
			ofNullable(getAveragingFeature()).ifPresent(builder::setAveragingFeature);
			ofNullable(getBarrier()).ifPresent(builder::setBarrier);
			ofNullable(getKnock()).ifPresent(builder::setKnock);
			ofNullable(getPassThrough()).ifPresent(builder::setPassThrough);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			OptionFeature _that = getType().cast(o);
		
			if (!ListEquals.listEquals(fxFeature, _that.getFxFeature())) return false;
			if (!Objects.equals(strategyFeature, _that.getStrategyFeature())) return false;
			if (!Objects.equals(averagingFeature, _that.getAveragingFeature())) return false;
			if (!Objects.equals(barrier, _that.getBarrier())) return false;
			if (!Objects.equals(knock, _that.getKnock())) return false;
			if (!Objects.equals(passThrough, _that.getPassThrough())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (fxFeature != null ? fxFeature.hashCode() : 0);
			_result = 31 * _result + (strategyFeature != null ? strategyFeature.hashCode() : 0);
			_result = 31 * _result + (averagingFeature != null ? averagingFeature.hashCode() : 0);
			_result = 31 * _result + (barrier != null ? barrier.hashCode() : 0);
			_result = 31 * _result + (knock != null ? knock.hashCode() : 0);
			_result = 31 * _result + (passThrough != null ? passThrough.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "OptionFeature {" +
				"fxFeature=" + this.fxFeature + ", " +
				"strategyFeature=" + this.strategyFeature + ", " +
				"averagingFeature=" + this.averagingFeature + ", " +
				"barrier=" + this.barrier + ", " +
				"knock=" + this.knock + ", " +
				"passThrough=" + this.passThrough +
			'}';
		}
	}

	/*********************** Builder Implementation of OptionFeature  ***********************/
	class OptionFeatureBuilderImpl implements OptionFeature.OptionFeatureBuilder {
	
		protected List<FxFeature.FxFeatureBuilder> fxFeature = new ArrayList<>();
		protected StrategyFeature.StrategyFeatureBuilder strategyFeature;
		protected AveragingCalculation.AveragingCalculationBuilder averagingFeature;
		protected Barrier.BarrierBuilder barrier;
		protected Knock.KnockBuilder knock;
		protected PassThrough.PassThroughBuilder passThrough;
	
		public OptionFeatureBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("fxFeature")
		public List<? extends FxFeature.FxFeatureBuilder> getFxFeature() {
			return fxFeature;
		}
		
		public FxFeature.FxFeatureBuilder getOrCreateFxFeature(int _index) {
		
			if (fxFeature==null) {
				this.fxFeature = new ArrayList<>();
			}
			FxFeature.FxFeatureBuilder result;
			return getIndex(fxFeature, _index, () -> {
						FxFeature.FxFeatureBuilder newFxFeature = FxFeature.builder();
						return newFxFeature;
					});
		}
		
		@Override
		@RosettaAttribute("strategyFeature")
		public StrategyFeature.StrategyFeatureBuilder getStrategyFeature() {
			return strategyFeature;
		}
		
		@Override
		public StrategyFeature.StrategyFeatureBuilder getOrCreateStrategyFeature() {
			StrategyFeature.StrategyFeatureBuilder result;
			if (strategyFeature!=null) {
				result = strategyFeature;
			}
			else {
				result = strategyFeature = StrategyFeature.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("averagingFeature")
		public AveragingCalculation.AveragingCalculationBuilder getAveragingFeature() {
			return averagingFeature;
		}
		
		@Override
		public AveragingCalculation.AveragingCalculationBuilder getOrCreateAveragingFeature() {
			AveragingCalculation.AveragingCalculationBuilder result;
			if (averagingFeature!=null) {
				result = averagingFeature;
			}
			else {
				result = averagingFeature = AveragingCalculation.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("barrier")
		public Barrier.BarrierBuilder getBarrier() {
			return barrier;
		}
		
		@Override
		public Barrier.BarrierBuilder getOrCreateBarrier() {
			Barrier.BarrierBuilder result;
			if (barrier!=null) {
				result = barrier;
			}
			else {
				result = barrier = Barrier.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("knock")
		public Knock.KnockBuilder getKnock() {
			return knock;
		}
		
		@Override
		public Knock.KnockBuilder getOrCreateKnock() {
			Knock.KnockBuilder result;
			if (knock!=null) {
				result = knock;
			}
			else {
				result = knock = Knock.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("passThrough")
		public PassThrough.PassThroughBuilder getPassThrough() {
			return passThrough;
		}
		
		@Override
		public PassThrough.PassThroughBuilder getOrCreatePassThrough() {
			PassThrough.PassThroughBuilder result;
			if (passThrough!=null) {
				result = passThrough;
			}
			else {
				result = passThrough = PassThrough.builder();
			}
			
			return result;
		}
	
		@Override
		public OptionFeature.OptionFeatureBuilder addFxFeature(FxFeature fxFeature) {
			if (fxFeature!=null) this.fxFeature.add(fxFeature.toBuilder());
			return this;
		}
		
		@Override
		public OptionFeature.OptionFeatureBuilder addFxFeature(FxFeature fxFeature, int _idx) {
			getIndex(this.fxFeature, _idx, () -> fxFeature.toBuilder());
			return this;
		}
		@Override 
		public OptionFeature.OptionFeatureBuilder addFxFeature(List<? extends FxFeature> fxFeatures) {
			if (fxFeatures != null) {
				for (FxFeature toAdd : fxFeatures) {
					this.fxFeature.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("fxFeature")
		public OptionFeature.OptionFeatureBuilder setFxFeature(List<? extends FxFeature> fxFeatures) {
			if (fxFeatures == null)  {
				this.fxFeature = new ArrayList<>();
			}
			else {
				this.fxFeature = fxFeatures.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("strategyFeature")
		public OptionFeature.OptionFeatureBuilder setStrategyFeature(StrategyFeature strategyFeature) {
			this.strategyFeature = strategyFeature==null?null:strategyFeature.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("averagingFeature")
		public OptionFeature.OptionFeatureBuilder setAveragingFeature(AveragingCalculation averagingFeature) {
			this.averagingFeature = averagingFeature==null?null:averagingFeature.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("barrier")
		public OptionFeature.OptionFeatureBuilder setBarrier(Barrier barrier) {
			this.barrier = barrier==null?null:barrier.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("knock")
		public OptionFeature.OptionFeatureBuilder setKnock(Knock knock) {
			this.knock = knock==null?null:knock.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("passThrough")
		public OptionFeature.OptionFeatureBuilder setPassThrough(PassThrough passThrough) {
			this.passThrough = passThrough==null?null:passThrough.toBuilder();
			return this;
		}
		
		@Override
		public OptionFeature build() {
			return new OptionFeature.OptionFeatureImpl(this);
		}
		
		@Override
		public OptionFeature.OptionFeatureBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public OptionFeature.OptionFeatureBuilder prune() {
			fxFeature = fxFeature.stream().filter(b->b!=null).<FxFeature.FxFeatureBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (strategyFeature!=null && !strategyFeature.prune().hasData()) strategyFeature = null;
			if (averagingFeature!=null && !averagingFeature.prune().hasData()) averagingFeature = null;
			if (barrier!=null && !barrier.prune().hasData()) barrier = null;
			if (knock!=null && !knock.prune().hasData()) knock = null;
			if (passThrough!=null && !passThrough.prune().hasData()) passThrough = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getFxFeature()!=null && getFxFeature().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getStrategyFeature()!=null && getStrategyFeature().hasData()) return true;
			if (getAveragingFeature()!=null && getAveragingFeature().hasData()) return true;
			if (getBarrier()!=null && getBarrier().hasData()) return true;
			if (getKnock()!=null && getKnock().hasData()) return true;
			if (getPassThrough()!=null && getPassThrough().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public OptionFeature.OptionFeatureBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			OptionFeature.OptionFeatureBuilder o = (OptionFeature.OptionFeatureBuilder) other;
			
			merger.mergeRosetta(getFxFeature(), o.getFxFeature(), this::getOrCreateFxFeature);
			merger.mergeRosetta(getStrategyFeature(), o.getStrategyFeature(), this::setStrategyFeature);
			merger.mergeRosetta(getAveragingFeature(), o.getAveragingFeature(), this::setAveragingFeature);
			merger.mergeRosetta(getBarrier(), o.getBarrier(), this::setBarrier);
			merger.mergeRosetta(getKnock(), o.getKnock(), this::setKnock);
			merger.mergeRosetta(getPassThrough(), o.getPassThrough(), this::setPassThrough);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			OptionFeature _that = getType().cast(o);
		
			if (!ListEquals.listEquals(fxFeature, _that.getFxFeature())) return false;
			if (!Objects.equals(strategyFeature, _that.getStrategyFeature())) return false;
			if (!Objects.equals(averagingFeature, _that.getAveragingFeature())) return false;
			if (!Objects.equals(barrier, _that.getBarrier())) return false;
			if (!Objects.equals(knock, _that.getKnock())) return false;
			if (!Objects.equals(passThrough, _that.getPassThrough())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (fxFeature != null ? fxFeature.hashCode() : 0);
			_result = 31 * _result + (strategyFeature != null ? strategyFeature.hashCode() : 0);
			_result = 31 * _result + (averagingFeature != null ? averagingFeature.hashCode() : 0);
			_result = 31 * _result + (barrier != null ? barrier.hashCode() : 0);
			_result = 31 * _result + (knock != null ? knock.hashCode() : 0);
			_result = 31 * _result + (passThrough != null ? passThrough.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "OptionFeatureBuilder {" +
				"fxFeature=" + this.fxFeature + ", " +
				"strategyFeature=" + this.strategyFeature + ", " +
				"averagingFeature=" + this.averagingFeature + ", " +
				"barrier=" + this.barrier + ", " +
				"knock=" + this.knock + ", " +
				"passThrough=" + this.passThrough +
			'}';
		}
	}
}
