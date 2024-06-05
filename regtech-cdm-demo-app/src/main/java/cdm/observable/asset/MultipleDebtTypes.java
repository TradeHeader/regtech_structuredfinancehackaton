package cdm.observable.asset;

import cdm.base.math.QuantifierEnum;
import cdm.observable.asset.MultipleDebtTypes;
import cdm.observable.asset.MultipleDebtTypes.MultipleDebtTypesBuilder;
import cdm.observable.asset.MultipleDebtTypes.MultipleDebtTypesBuilderImpl;
import cdm.observable.asset.MultipleDebtTypes.MultipleDebtTypesImpl;
import cdm.observable.asset.meta.MultipleDebtTypesMeta;
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
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Represents a class to specify multiple credit debt types alongside a conditional &#39;any&#39; or &#39;all&#39; qualifier.
 * @version ${project.version}
 */
@RosettaDataType(value="MultipleDebtTypes", builder=MultipleDebtTypes.MultipleDebtTypesBuilderImpl.class, version="${project.version}")
public interface MultipleDebtTypes extends RosettaModelObject {

	MultipleDebtTypesMeta metaData = new MultipleDebtTypesMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * An enumerated attribute, to qualify whether All or Any debt type applies.
	 */
	QuantifierEnum getCondition();
	/**
	 * The type of debt, e.g. long term debt, deposit, ... FpML doesn&#39;t specific a scheme value, hence no enumeration is specified as part of the CDM. At least two debt types much be specified.
	 */
	List<? extends FieldWithMetaString> getDebtType();

	/*********************** Build Methods  ***********************/
	MultipleDebtTypes build();
	
	MultipleDebtTypes.MultipleDebtTypesBuilder toBuilder();
	
	static MultipleDebtTypes.MultipleDebtTypesBuilder builder() {
		return new MultipleDebtTypes.MultipleDebtTypesBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends MultipleDebtTypes> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends MultipleDebtTypes> getType() {
		return MultipleDebtTypes.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("condition"), QuantifierEnum.class, getCondition(), this);
		processRosetta(path.newSubPath("debtType"), processor, FieldWithMetaString.class, getDebtType());
	}
	

	/*********************** Builder Interface  ***********************/
	interface MultipleDebtTypesBuilder extends MultipleDebtTypes, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateDebtType(int _index);
		List<? extends FieldWithMetaString.FieldWithMetaStringBuilder> getDebtType();
		MultipleDebtTypes.MultipleDebtTypesBuilder setCondition(QuantifierEnum condition);
		MultipleDebtTypes.MultipleDebtTypesBuilder addDebtType(FieldWithMetaString debtType0);
		MultipleDebtTypes.MultipleDebtTypesBuilder addDebtType(FieldWithMetaString debtType1, int _idx);
		MultipleDebtTypes.MultipleDebtTypesBuilder addDebtTypeValue(String debtType2);
		MultipleDebtTypes.MultipleDebtTypesBuilder addDebtTypeValue(String debtType3, int _idx);
		MultipleDebtTypes.MultipleDebtTypesBuilder addDebtType(List<? extends FieldWithMetaString> debtType4);
		MultipleDebtTypes.MultipleDebtTypesBuilder setDebtType(List<? extends FieldWithMetaString> debtType5);
		MultipleDebtTypes.MultipleDebtTypesBuilder addDebtTypeValue(List<? extends String> debtType6);
		MultipleDebtTypes.MultipleDebtTypesBuilder setDebtTypeValue(List<? extends String> debtType7);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("condition"), QuantifierEnum.class, getCondition(), this);
			processRosetta(path.newSubPath("debtType"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getDebtType());
		}
		

		MultipleDebtTypes.MultipleDebtTypesBuilder prune();
	}

	/*********************** Immutable Implementation of MultipleDebtTypes  ***********************/
	class MultipleDebtTypesImpl implements MultipleDebtTypes {
		private final QuantifierEnum condition;
		private final List<? extends FieldWithMetaString> debtType;
		
		protected MultipleDebtTypesImpl(MultipleDebtTypes.MultipleDebtTypesBuilder builder) {
			this.condition = builder.getCondition();
			this.debtType = ofNullable(builder.getDebtType()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("condition")
		public QuantifierEnum getCondition() {
			return condition;
		}
		
		@Override
		@RosettaAttribute("debtType")
		public List<? extends FieldWithMetaString> getDebtType() {
			return debtType;
		}
		
		@Override
		public MultipleDebtTypes build() {
			return this;
		}
		
		@Override
		public MultipleDebtTypes.MultipleDebtTypesBuilder toBuilder() {
			MultipleDebtTypes.MultipleDebtTypesBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(MultipleDebtTypes.MultipleDebtTypesBuilder builder) {
			ofNullable(getCondition()).ifPresent(builder::setCondition);
			ofNullable(getDebtType()).ifPresent(builder::setDebtType);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			MultipleDebtTypes _that = getType().cast(o);
		
			if (!Objects.equals(condition, _that.getCondition())) return false;
			if (!ListEquals.listEquals(debtType, _that.getDebtType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (condition != null ? condition.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (debtType != null ? debtType.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MultipleDebtTypes {" +
				"condition=" + this.condition + ", " +
				"debtType=" + this.debtType +
			'}';
		}
	}

	/*********************** Builder Implementation of MultipleDebtTypes  ***********************/
	class MultipleDebtTypesBuilderImpl implements MultipleDebtTypes.MultipleDebtTypesBuilder {
	
		protected QuantifierEnum condition;
		protected List<FieldWithMetaString.FieldWithMetaStringBuilder> debtType = new ArrayList<>();
	
		public MultipleDebtTypesBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("condition")
		public QuantifierEnum getCondition() {
			return condition;
		}
		
		@Override
		@RosettaAttribute("debtType")
		public List<? extends FieldWithMetaString.FieldWithMetaStringBuilder> getDebtType() {
			return debtType;
		}
		
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateDebtType(int _index) {
		
			if (debtType==null) {
				this.debtType = new ArrayList<>();
			}
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			return getIndex(debtType, _index, () -> {
						FieldWithMetaString.FieldWithMetaStringBuilder newDebtType = FieldWithMetaString.builder();
						return newDebtType;
					});
		}
		
	
		@Override
		@RosettaAttribute("condition")
		public MultipleDebtTypes.MultipleDebtTypesBuilder setCondition(QuantifierEnum condition) {
			this.condition = condition==null?null:condition;
			return this;
		}
		@Override
		public MultipleDebtTypes.MultipleDebtTypesBuilder addDebtType(FieldWithMetaString debtType) {
			if (debtType!=null) this.debtType.add(debtType.toBuilder());
			return this;
		}
		
		@Override
		public MultipleDebtTypes.MultipleDebtTypesBuilder addDebtType(FieldWithMetaString debtType, int _idx) {
			getIndex(this.debtType, _idx, () -> debtType.toBuilder());
			return this;
		}
		
		@Override
		public MultipleDebtTypes.MultipleDebtTypesBuilder addDebtTypeValue(String debtType) {
			this.getOrCreateDebtType(-1).setValue(debtType);
			return this;
		}
		
		@Override
		public MultipleDebtTypes.MultipleDebtTypesBuilder addDebtTypeValue(String debtType, int _idx) {
			this.getOrCreateDebtType(_idx).setValue(debtType);
			return this;
		}
		@Override 
		public MultipleDebtTypes.MultipleDebtTypesBuilder addDebtType(List<? extends FieldWithMetaString> debtTypes) {
			if (debtTypes != null) {
				for (FieldWithMetaString toAdd : debtTypes) {
					this.debtType.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("debtType")
		public MultipleDebtTypes.MultipleDebtTypesBuilder setDebtType(List<? extends FieldWithMetaString> debtTypes) {
			if (debtTypes == null)  {
				this.debtType = new ArrayList<>();
			}
			else {
				this.debtType = debtTypes.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public MultipleDebtTypes.MultipleDebtTypesBuilder addDebtTypeValue(List<? extends String> debtTypes) {
			if (debtTypes != null) {
				for (String toAdd : debtTypes) {
					this.addDebtTypeValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public MultipleDebtTypes.MultipleDebtTypesBuilder setDebtTypeValue(List<? extends String> debtTypes) {
			this.debtType.clear();
			if (debtTypes!=null) {
				debtTypes.forEach(this::addDebtTypeValue);
			}
			return this;
		}
		
		
		@Override
		public MultipleDebtTypes build() {
			return new MultipleDebtTypes.MultipleDebtTypesImpl(this);
		}
		
		@Override
		public MultipleDebtTypes.MultipleDebtTypesBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MultipleDebtTypes.MultipleDebtTypesBuilder prune() {
			debtType = debtType.stream().filter(b->b!=null).<FieldWithMetaString.FieldWithMetaStringBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCondition()!=null) return true;
			if (getDebtType()!=null && !getDebtType().isEmpty()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MultipleDebtTypes.MultipleDebtTypesBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			MultipleDebtTypes.MultipleDebtTypesBuilder o = (MultipleDebtTypes.MultipleDebtTypesBuilder) other;
			
			merger.mergeRosetta(getDebtType(), o.getDebtType(), this::getOrCreateDebtType);
			
			merger.mergeBasic(getCondition(), o.getCondition(), this::setCondition);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			MultipleDebtTypes _that = getType().cast(o);
		
			if (!Objects.equals(condition, _that.getCondition())) return false;
			if (!ListEquals.listEquals(debtType, _that.getDebtType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (condition != null ? condition.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (debtType != null ? debtType.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MultipleDebtTypesBuilder {" +
				"condition=" + this.condition + ", " +
				"debtType=" + this.debtType +
			'}';
		}
	}
}
