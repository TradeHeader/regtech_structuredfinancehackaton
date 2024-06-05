package cdm.product.collateral;

import cdm.product.collateral.CollateralTreatment;
import cdm.product.collateral.CollateralTreatment.CollateralTreatmentBuilder;
import cdm.product.collateral.CollateralTreatment.CollateralTreatmentBuilderImpl;
import cdm.product.collateral.CollateralTreatment.CollateralTreatmentImpl;
import cdm.product.collateral.CollateralValuationTreatment;
import cdm.product.collateral.ConcentrationLimit;
import cdm.product.collateral.meta.CollateralTreatmentMeta;
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
 * Specifies the treatment terms for the eligible collateral criteria specified.
 * @version ${project.version}
 */
@RosettaDataType(value="CollateralTreatment", builder=CollateralTreatment.CollateralTreatmentBuilderImpl.class, version="${project.version}")
public interface CollateralTreatment extends RosettaModelObject {

	CollateralTreatmentMeta metaData = new CollateralTreatmentMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specification of the valuation treatment for the specified collateral.
	 *
	 * Body ICMA
	 * Corpus MasterAgreement GMRA Global Master Repurchase Agreement GMRA 2011 "The Global Master Repurchase Agreement (GMRA) is a model legal agreement designed for parties transacting repos and is published by the International Capital Market Association (ICMA)." 
	 * namingConvention "marginRatio"
	 *
	 * Provision 
	 *
	 */
	CollateralValuationTreatment getValuationTreatment();
	/**
	 * Specification of concentration limits applicable to the collateral criteria.
	 */
	List<? extends ConcentrationLimit> getConcentrationLimit();
	/**
	 * A boolean attribute to specify whether collateral critieria are inclusion (True) or exclusion (False) criteria.
	 */
	Boolean getIsIncluded();

	/*********************** Build Methods  ***********************/
	CollateralTreatment build();
	
	CollateralTreatment.CollateralTreatmentBuilder toBuilder();
	
	static CollateralTreatment.CollateralTreatmentBuilder builder() {
		return new CollateralTreatment.CollateralTreatmentBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CollateralTreatment> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CollateralTreatment> getType() {
		return CollateralTreatment.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("valuationTreatment"), processor, CollateralValuationTreatment.class, getValuationTreatment());
		processRosetta(path.newSubPath("concentrationLimit"), processor, ConcentrationLimit.class, getConcentrationLimit());
		processor.processBasic(path.newSubPath("isIncluded"), Boolean.class, getIsIncluded(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface CollateralTreatmentBuilder extends CollateralTreatment, RosettaModelObjectBuilder {
		CollateralValuationTreatment.CollateralValuationTreatmentBuilder getOrCreateValuationTreatment();
		CollateralValuationTreatment.CollateralValuationTreatmentBuilder getValuationTreatment();
		ConcentrationLimit.ConcentrationLimitBuilder getOrCreateConcentrationLimit(int _index);
		List<? extends ConcentrationLimit.ConcentrationLimitBuilder> getConcentrationLimit();
		CollateralTreatment.CollateralTreatmentBuilder setValuationTreatment(CollateralValuationTreatment valuationTreatment);
		CollateralTreatment.CollateralTreatmentBuilder addConcentrationLimit(ConcentrationLimit concentrationLimit0);
		CollateralTreatment.CollateralTreatmentBuilder addConcentrationLimit(ConcentrationLimit concentrationLimit1, int _idx);
		CollateralTreatment.CollateralTreatmentBuilder addConcentrationLimit(List<? extends ConcentrationLimit> concentrationLimit2);
		CollateralTreatment.CollateralTreatmentBuilder setConcentrationLimit(List<? extends ConcentrationLimit> concentrationLimit3);
		CollateralTreatment.CollateralTreatmentBuilder setIsIncluded(Boolean isIncluded);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("valuationTreatment"), processor, CollateralValuationTreatment.CollateralValuationTreatmentBuilder.class, getValuationTreatment());
			processRosetta(path.newSubPath("concentrationLimit"), processor, ConcentrationLimit.ConcentrationLimitBuilder.class, getConcentrationLimit());
			processor.processBasic(path.newSubPath("isIncluded"), Boolean.class, getIsIncluded(), this);
		}
		

		CollateralTreatment.CollateralTreatmentBuilder prune();
	}

	/*********************** Immutable Implementation of CollateralTreatment  ***********************/
	class CollateralTreatmentImpl implements CollateralTreatment {
		private final CollateralValuationTreatment valuationTreatment;
		private final List<? extends ConcentrationLimit> concentrationLimit;
		private final Boolean isIncluded;
		
		protected CollateralTreatmentImpl(CollateralTreatment.CollateralTreatmentBuilder builder) {
			this.valuationTreatment = ofNullable(builder.getValuationTreatment()).map(f->f.build()).orElse(null);
			this.concentrationLimit = ofNullable(builder.getConcentrationLimit()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.isIncluded = builder.getIsIncluded();
		}
		
		@Override
		@RosettaAttribute("valuationTreatment")
		public CollateralValuationTreatment getValuationTreatment() {
			return valuationTreatment;
		}
		
		@Override
		@RosettaAttribute("concentrationLimit")
		public List<? extends ConcentrationLimit> getConcentrationLimit() {
			return concentrationLimit;
		}
		
		@Override
		@RosettaAttribute("isIncluded")
		public Boolean getIsIncluded() {
			return isIncluded;
		}
		
		@Override
		public CollateralTreatment build() {
			return this;
		}
		
		@Override
		public CollateralTreatment.CollateralTreatmentBuilder toBuilder() {
			CollateralTreatment.CollateralTreatmentBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CollateralTreatment.CollateralTreatmentBuilder builder) {
			ofNullable(getValuationTreatment()).ifPresent(builder::setValuationTreatment);
			ofNullable(getConcentrationLimit()).ifPresent(builder::setConcentrationLimit);
			ofNullable(getIsIncluded()).ifPresent(builder::setIsIncluded);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralTreatment _that = getType().cast(o);
		
			if (!Objects.equals(valuationTreatment, _that.getValuationTreatment())) return false;
			if (!ListEquals.listEquals(concentrationLimit, _that.getConcentrationLimit())) return false;
			if (!Objects.equals(isIncluded, _that.getIsIncluded())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (valuationTreatment != null ? valuationTreatment.hashCode() : 0);
			_result = 31 * _result + (concentrationLimit != null ? concentrationLimit.hashCode() : 0);
			_result = 31 * _result + (isIncluded != null ? isIncluded.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralTreatment {" +
				"valuationTreatment=" + this.valuationTreatment + ", " +
				"concentrationLimit=" + this.concentrationLimit + ", " +
				"isIncluded=" + this.isIncluded +
			'}';
		}
	}

	/*********************** Builder Implementation of CollateralTreatment  ***********************/
	class CollateralTreatmentBuilderImpl implements CollateralTreatment.CollateralTreatmentBuilder {
	
		protected CollateralValuationTreatment.CollateralValuationTreatmentBuilder valuationTreatment;
		protected List<ConcentrationLimit.ConcentrationLimitBuilder> concentrationLimit = new ArrayList<>();
		protected Boolean isIncluded;
	
		public CollateralTreatmentBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("valuationTreatment")
		public CollateralValuationTreatment.CollateralValuationTreatmentBuilder getValuationTreatment() {
			return valuationTreatment;
		}
		
		@Override
		public CollateralValuationTreatment.CollateralValuationTreatmentBuilder getOrCreateValuationTreatment() {
			CollateralValuationTreatment.CollateralValuationTreatmentBuilder result;
			if (valuationTreatment!=null) {
				result = valuationTreatment;
			}
			else {
				result = valuationTreatment = CollateralValuationTreatment.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("concentrationLimit")
		public List<? extends ConcentrationLimit.ConcentrationLimitBuilder> getConcentrationLimit() {
			return concentrationLimit;
		}
		
		public ConcentrationLimit.ConcentrationLimitBuilder getOrCreateConcentrationLimit(int _index) {
		
			if (concentrationLimit==null) {
				this.concentrationLimit = new ArrayList<>();
			}
			ConcentrationLimit.ConcentrationLimitBuilder result;
			return getIndex(concentrationLimit, _index, () -> {
						ConcentrationLimit.ConcentrationLimitBuilder newConcentrationLimit = ConcentrationLimit.builder();
						return newConcentrationLimit;
					});
		}
		
		@Override
		@RosettaAttribute("isIncluded")
		public Boolean getIsIncluded() {
			return isIncluded;
		}
		
	
		@Override
		@RosettaAttribute("valuationTreatment")
		public CollateralTreatment.CollateralTreatmentBuilder setValuationTreatment(CollateralValuationTreatment valuationTreatment) {
			this.valuationTreatment = valuationTreatment==null?null:valuationTreatment.toBuilder();
			return this;
		}
		@Override
		public CollateralTreatment.CollateralTreatmentBuilder addConcentrationLimit(ConcentrationLimit concentrationLimit) {
			if (concentrationLimit!=null) this.concentrationLimit.add(concentrationLimit.toBuilder());
			return this;
		}
		
		@Override
		public CollateralTreatment.CollateralTreatmentBuilder addConcentrationLimit(ConcentrationLimit concentrationLimit, int _idx) {
			getIndex(this.concentrationLimit, _idx, () -> concentrationLimit.toBuilder());
			return this;
		}
		@Override 
		public CollateralTreatment.CollateralTreatmentBuilder addConcentrationLimit(List<? extends ConcentrationLimit> concentrationLimits) {
			if (concentrationLimits != null) {
				for (ConcentrationLimit toAdd : concentrationLimits) {
					this.concentrationLimit.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("concentrationLimit")
		public CollateralTreatment.CollateralTreatmentBuilder setConcentrationLimit(List<? extends ConcentrationLimit> concentrationLimits) {
			if (concentrationLimits == null)  {
				this.concentrationLimit = new ArrayList<>();
			}
			else {
				this.concentrationLimit = concentrationLimits.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("isIncluded")
		public CollateralTreatment.CollateralTreatmentBuilder setIsIncluded(Boolean isIncluded) {
			this.isIncluded = isIncluded==null?null:isIncluded;
			return this;
		}
		
		@Override
		public CollateralTreatment build() {
			return new CollateralTreatment.CollateralTreatmentImpl(this);
		}
		
		@Override
		public CollateralTreatment.CollateralTreatmentBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralTreatment.CollateralTreatmentBuilder prune() {
			if (valuationTreatment!=null && !valuationTreatment.prune().hasData()) valuationTreatment = null;
			concentrationLimit = concentrationLimit.stream().filter(b->b!=null).<ConcentrationLimit.ConcentrationLimitBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getValuationTreatment()!=null && getValuationTreatment().hasData()) return true;
			if (getConcentrationLimit()!=null && getConcentrationLimit().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getIsIncluded()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralTreatment.CollateralTreatmentBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CollateralTreatment.CollateralTreatmentBuilder o = (CollateralTreatment.CollateralTreatmentBuilder) other;
			
			merger.mergeRosetta(getValuationTreatment(), o.getValuationTreatment(), this::setValuationTreatment);
			merger.mergeRosetta(getConcentrationLimit(), o.getConcentrationLimit(), this::getOrCreateConcentrationLimit);
			
			merger.mergeBasic(getIsIncluded(), o.getIsIncluded(), this::setIsIncluded);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralTreatment _that = getType().cast(o);
		
			if (!Objects.equals(valuationTreatment, _that.getValuationTreatment())) return false;
			if (!ListEquals.listEquals(concentrationLimit, _that.getConcentrationLimit())) return false;
			if (!Objects.equals(isIncluded, _that.getIsIncluded())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (valuationTreatment != null ? valuationTreatment.hashCode() : 0);
			_result = 31 * _result + (concentrationLimit != null ? concentrationLimit.hashCode() : 0);
			_result = 31 * _result + (isIncluded != null ? isIncluded.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralTreatmentBuilder {" +
				"valuationTreatment=" + this.valuationTreatment + ", " +
				"concentrationLimit=" + this.concentrationLimit + ", " +
				"isIncluded=" + this.isIncluded +
			'}';
		}
	}
}
