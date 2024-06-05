package cdm.product.collateral;

import cdm.base.math.MoneyRange;
import cdm.base.math.NumberRange;
import cdm.product.collateral.ConcentrationLimit;
import cdm.product.collateral.ConcentrationLimit.ConcentrationLimitBuilder;
import cdm.product.collateral.ConcentrationLimit.ConcentrationLimitBuilderImpl;
import cdm.product.collateral.ConcentrationLimit.ConcentrationLimitImpl;
import cdm.product.collateral.ConcentrationLimitCriteria;
import cdm.product.collateral.meta.ConcentrationLimitMeta;
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
 * Represents a class to describe concentration limits that may be applicable to eligible collateral criteria.
 * @version ${project.version}
 */
@RosettaDataType(value="ConcentrationLimit", builder=ConcentrationLimit.ConcentrationLimitBuilderImpl.class, version="${project.version}")
public interface ConcentrationLimit extends RosettaModelObject {

	ConcentrationLimitMeta metaData = new ConcentrationLimitMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies a set of criteria to describe the assets that the concentration limits apply to.
	 */
	List<? extends ConcentrationLimitCriteria> getConcentrationLimitCriteria();
	/**
	 * Specifies the value of collateral limit represented as a range.
	 */
	MoneyRange getValueLimit();
	/**
	 * Specifies the perecentage of collateral limit represented as a decimal number - example 25% is 0.25.
	 */
	NumberRange getPercentageLimit();

	/*********************** Build Methods  ***********************/
	ConcentrationLimit build();
	
	ConcentrationLimit.ConcentrationLimitBuilder toBuilder();
	
	static ConcentrationLimit.ConcentrationLimitBuilder builder() {
		return new ConcentrationLimit.ConcentrationLimitBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ConcentrationLimit> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ConcentrationLimit> getType() {
		return ConcentrationLimit.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("concentrationLimitCriteria"), processor, ConcentrationLimitCriteria.class, getConcentrationLimitCriteria());
		processRosetta(path.newSubPath("valueLimit"), processor, MoneyRange.class, getValueLimit());
		processRosetta(path.newSubPath("percentageLimit"), processor, NumberRange.class, getPercentageLimit());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ConcentrationLimitBuilder extends ConcentrationLimit, RosettaModelObjectBuilder {
		ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder getOrCreateConcentrationLimitCriteria(int _index);
		List<? extends ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder> getConcentrationLimitCriteria();
		MoneyRange.MoneyRangeBuilder getOrCreateValueLimit();
		MoneyRange.MoneyRangeBuilder getValueLimit();
		NumberRange.NumberRangeBuilder getOrCreatePercentageLimit();
		NumberRange.NumberRangeBuilder getPercentageLimit();
		ConcentrationLimit.ConcentrationLimitBuilder addConcentrationLimitCriteria(ConcentrationLimitCriteria concentrationLimitCriteria0);
		ConcentrationLimit.ConcentrationLimitBuilder addConcentrationLimitCriteria(ConcentrationLimitCriteria concentrationLimitCriteria1, int _idx);
		ConcentrationLimit.ConcentrationLimitBuilder addConcentrationLimitCriteria(List<? extends ConcentrationLimitCriteria> concentrationLimitCriteria2);
		ConcentrationLimit.ConcentrationLimitBuilder setConcentrationLimitCriteria(List<? extends ConcentrationLimitCriteria> concentrationLimitCriteria3);
		ConcentrationLimit.ConcentrationLimitBuilder setValueLimit(MoneyRange valueLimit);
		ConcentrationLimit.ConcentrationLimitBuilder setPercentageLimit(NumberRange percentageLimit);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("concentrationLimitCriteria"), processor, ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder.class, getConcentrationLimitCriteria());
			processRosetta(path.newSubPath("valueLimit"), processor, MoneyRange.MoneyRangeBuilder.class, getValueLimit());
			processRosetta(path.newSubPath("percentageLimit"), processor, NumberRange.NumberRangeBuilder.class, getPercentageLimit());
		}
		

		ConcentrationLimit.ConcentrationLimitBuilder prune();
	}

	/*********************** Immutable Implementation of ConcentrationLimit  ***********************/
	class ConcentrationLimitImpl implements ConcentrationLimit {
		private final List<? extends ConcentrationLimitCriteria> concentrationLimitCriteria;
		private final MoneyRange valueLimit;
		private final NumberRange percentageLimit;
		
		protected ConcentrationLimitImpl(ConcentrationLimit.ConcentrationLimitBuilder builder) {
			this.concentrationLimitCriteria = ofNullable(builder.getConcentrationLimitCriteria()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.valueLimit = ofNullable(builder.getValueLimit()).map(f->f.build()).orElse(null);
			this.percentageLimit = ofNullable(builder.getPercentageLimit()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("concentrationLimitCriteria")
		public List<? extends ConcentrationLimitCriteria> getConcentrationLimitCriteria() {
			return concentrationLimitCriteria;
		}
		
		@Override
		@RosettaAttribute("valueLimit")
		public MoneyRange getValueLimit() {
			return valueLimit;
		}
		
		@Override
		@RosettaAttribute("percentageLimit")
		public NumberRange getPercentageLimit() {
			return percentageLimit;
		}
		
		@Override
		public ConcentrationLimit build() {
			return this;
		}
		
		@Override
		public ConcentrationLimit.ConcentrationLimitBuilder toBuilder() {
			ConcentrationLimit.ConcentrationLimitBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ConcentrationLimit.ConcentrationLimitBuilder builder) {
			ofNullable(getConcentrationLimitCriteria()).ifPresent(builder::setConcentrationLimitCriteria);
			ofNullable(getValueLimit()).ifPresent(builder::setValueLimit);
			ofNullable(getPercentageLimit()).ifPresent(builder::setPercentageLimit);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ConcentrationLimit _that = getType().cast(o);
		
			if (!ListEquals.listEquals(concentrationLimitCriteria, _that.getConcentrationLimitCriteria())) return false;
			if (!Objects.equals(valueLimit, _that.getValueLimit())) return false;
			if (!Objects.equals(percentageLimit, _that.getPercentageLimit())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (concentrationLimitCriteria != null ? concentrationLimitCriteria.hashCode() : 0);
			_result = 31 * _result + (valueLimit != null ? valueLimit.hashCode() : 0);
			_result = 31 * _result + (percentageLimit != null ? percentageLimit.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ConcentrationLimit {" +
				"concentrationLimitCriteria=" + this.concentrationLimitCriteria + ", " +
				"valueLimit=" + this.valueLimit + ", " +
				"percentageLimit=" + this.percentageLimit +
			'}';
		}
	}

	/*********************** Builder Implementation of ConcentrationLimit  ***********************/
	class ConcentrationLimitBuilderImpl implements ConcentrationLimit.ConcentrationLimitBuilder {
	
		protected List<ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder> concentrationLimitCriteria = new ArrayList<>();
		protected MoneyRange.MoneyRangeBuilder valueLimit;
		protected NumberRange.NumberRangeBuilder percentageLimit;
	
		public ConcentrationLimitBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("concentrationLimitCriteria")
		public List<? extends ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder> getConcentrationLimitCriteria() {
			return concentrationLimitCriteria;
		}
		
		public ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder getOrCreateConcentrationLimitCriteria(int _index) {
		
			if (concentrationLimitCriteria==null) {
				this.concentrationLimitCriteria = new ArrayList<>();
			}
			ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder result;
			return getIndex(concentrationLimitCriteria, _index, () -> {
						ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder newConcentrationLimitCriteria = ConcentrationLimitCriteria.builder();
						return newConcentrationLimitCriteria;
					});
		}
		
		@Override
		@RosettaAttribute("valueLimit")
		public MoneyRange.MoneyRangeBuilder getValueLimit() {
			return valueLimit;
		}
		
		@Override
		public MoneyRange.MoneyRangeBuilder getOrCreateValueLimit() {
			MoneyRange.MoneyRangeBuilder result;
			if (valueLimit!=null) {
				result = valueLimit;
			}
			else {
				result = valueLimit = MoneyRange.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("percentageLimit")
		public NumberRange.NumberRangeBuilder getPercentageLimit() {
			return percentageLimit;
		}
		
		@Override
		public NumberRange.NumberRangeBuilder getOrCreatePercentageLimit() {
			NumberRange.NumberRangeBuilder result;
			if (percentageLimit!=null) {
				result = percentageLimit;
			}
			else {
				result = percentageLimit = NumberRange.builder();
			}
			
			return result;
		}
	
		@Override
		public ConcentrationLimit.ConcentrationLimitBuilder addConcentrationLimitCriteria(ConcentrationLimitCriteria concentrationLimitCriteria) {
			if (concentrationLimitCriteria!=null) this.concentrationLimitCriteria.add(concentrationLimitCriteria.toBuilder());
			return this;
		}
		
		@Override
		public ConcentrationLimit.ConcentrationLimitBuilder addConcentrationLimitCriteria(ConcentrationLimitCriteria concentrationLimitCriteria, int _idx) {
			getIndex(this.concentrationLimitCriteria, _idx, () -> concentrationLimitCriteria.toBuilder());
			return this;
		}
		@Override 
		public ConcentrationLimit.ConcentrationLimitBuilder addConcentrationLimitCriteria(List<? extends ConcentrationLimitCriteria> concentrationLimitCriterias) {
			if (concentrationLimitCriterias != null) {
				for (ConcentrationLimitCriteria toAdd : concentrationLimitCriterias) {
					this.concentrationLimitCriteria.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("concentrationLimitCriteria")
		public ConcentrationLimit.ConcentrationLimitBuilder setConcentrationLimitCriteria(List<? extends ConcentrationLimitCriteria> concentrationLimitCriterias) {
			if (concentrationLimitCriterias == null)  {
				this.concentrationLimitCriteria = new ArrayList<>();
			}
			else {
				this.concentrationLimitCriteria = concentrationLimitCriterias.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("valueLimit")
		public ConcentrationLimit.ConcentrationLimitBuilder setValueLimit(MoneyRange valueLimit) {
			this.valueLimit = valueLimit==null?null:valueLimit.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("percentageLimit")
		public ConcentrationLimit.ConcentrationLimitBuilder setPercentageLimit(NumberRange percentageLimit) {
			this.percentageLimit = percentageLimit==null?null:percentageLimit.toBuilder();
			return this;
		}
		
		@Override
		public ConcentrationLimit build() {
			return new ConcentrationLimit.ConcentrationLimitImpl(this);
		}
		
		@Override
		public ConcentrationLimit.ConcentrationLimitBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ConcentrationLimit.ConcentrationLimitBuilder prune() {
			concentrationLimitCriteria = concentrationLimitCriteria.stream().filter(b->b!=null).<ConcentrationLimitCriteria.ConcentrationLimitCriteriaBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (valueLimit!=null && !valueLimit.prune().hasData()) valueLimit = null;
			if (percentageLimit!=null && !percentageLimit.prune().hasData()) percentageLimit = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getConcentrationLimitCriteria()!=null && getConcentrationLimitCriteria().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getValueLimit()!=null && getValueLimit().hasData()) return true;
			if (getPercentageLimit()!=null && getPercentageLimit().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ConcentrationLimit.ConcentrationLimitBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ConcentrationLimit.ConcentrationLimitBuilder o = (ConcentrationLimit.ConcentrationLimitBuilder) other;
			
			merger.mergeRosetta(getConcentrationLimitCriteria(), o.getConcentrationLimitCriteria(), this::getOrCreateConcentrationLimitCriteria);
			merger.mergeRosetta(getValueLimit(), o.getValueLimit(), this::setValueLimit);
			merger.mergeRosetta(getPercentageLimit(), o.getPercentageLimit(), this::setPercentageLimit);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ConcentrationLimit _that = getType().cast(o);
		
			if (!ListEquals.listEquals(concentrationLimitCriteria, _that.getConcentrationLimitCriteria())) return false;
			if (!Objects.equals(valueLimit, _that.getValueLimit())) return false;
			if (!Objects.equals(percentageLimit, _that.getPercentageLimit())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (concentrationLimitCriteria != null ? concentrationLimitCriteria.hashCode() : 0);
			_result = 31 * _result + (valueLimit != null ? valueLimit.hashCode() : 0);
			_result = 31 * _result + (percentageLimit != null ? percentageLimit.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ConcentrationLimitBuilder {" +
				"concentrationLimitCriteria=" + this.concentrationLimitCriteria + ", " +
				"valueLimit=" + this.valueLimit + ", " +
				"percentageLimit=" + this.percentageLimit +
			'}';
		}
	}
}
