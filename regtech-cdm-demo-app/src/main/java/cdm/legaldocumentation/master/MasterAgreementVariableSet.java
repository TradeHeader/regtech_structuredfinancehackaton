package cdm.legaldocumentation.master;

import cdm.legaldocumentation.master.MasterAgreementVariableSet;
import cdm.legaldocumentation.master.MasterAgreementVariableSet.MasterAgreementVariableSetBuilder;
import cdm.legaldocumentation.master.MasterAgreementVariableSet.MasterAgreementVariableSetBuilderImpl;
import cdm.legaldocumentation.master.MasterAgreementVariableSet.MasterAgreementVariableSetImpl;
import cdm.legaldocumentation.master.meta.MasterAgreementVariableSetMeta;
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
 * Defines a type where additional variables associated to clauses and their variants can be described.
 * @version ${project.version}
 */
@RosettaDataType(value="MasterAgreementVariableSet", builder=MasterAgreementVariableSet.MasterAgreementVariableSetBuilderImpl.class, version="${project.version}")
public interface MasterAgreementVariableSet extends RosettaModelObject {

	MasterAgreementVariableSetMeta metaData = new MasterAgreementVariableSetMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * For some variants a table of variables is required. To support this use case we need to be able to specify variables within variables. Including a variable set here gives us infinite nesting opportunities - realistically we are only ever expecting that a table would need to be defined for any particular clause, so we would expect two levels of nesting as a maximum i.e. variableSet-&gt;variableSet-&gt;name/value.
	 */
	List<? extends MasterAgreementVariableSet> getVariableSet();
	/**
	 * The name of the variable
	 */
	String getName();
	/**
	 * The value for this variable
	 */
	String getValue();

	/*********************** Build Methods  ***********************/
	MasterAgreementVariableSet build();
	
	MasterAgreementVariableSet.MasterAgreementVariableSetBuilder toBuilder();
	
	static MasterAgreementVariableSet.MasterAgreementVariableSetBuilder builder() {
		return new MasterAgreementVariableSet.MasterAgreementVariableSetBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends MasterAgreementVariableSet> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends MasterAgreementVariableSet> getType() {
		return MasterAgreementVariableSet.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("variableSet"), processor, MasterAgreementVariableSet.class, getVariableSet());
		processor.processBasic(path.newSubPath("name"), String.class, getName(), this);
		processor.processBasic(path.newSubPath("value"), String.class, getValue(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface MasterAgreementVariableSetBuilder extends MasterAgreementVariableSet, RosettaModelObjectBuilder {
		MasterAgreementVariableSet.MasterAgreementVariableSetBuilder getOrCreateVariableSet(int _index);
		List<? extends MasterAgreementVariableSet.MasterAgreementVariableSetBuilder> getVariableSet();
		MasterAgreementVariableSet.MasterAgreementVariableSetBuilder addVariableSet(MasterAgreementVariableSet variableSet0);
		MasterAgreementVariableSet.MasterAgreementVariableSetBuilder addVariableSet(MasterAgreementVariableSet variableSet1, int _idx);
		MasterAgreementVariableSet.MasterAgreementVariableSetBuilder addVariableSet(List<? extends MasterAgreementVariableSet> variableSet2);
		MasterAgreementVariableSet.MasterAgreementVariableSetBuilder setVariableSet(List<? extends MasterAgreementVariableSet> variableSet3);
		MasterAgreementVariableSet.MasterAgreementVariableSetBuilder setName(String name);
		MasterAgreementVariableSet.MasterAgreementVariableSetBuilder setValue(String value);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("variableSet"), processor, MasterAgreementVariableSet.MasterAgreementVariableSetBuilder.class, getVariableSet());
			processor.processBasic(path.newSubPath("name"), String.class, getName(), this);
			processor.processBasic(path.newSubPath("value"), String.class, getValue(), this);
		}
		

		MasterAgreementVariableSet.MasterAgreementVariableSetBuilder prune();
	}

	/*********************** Immutable Implementation of MasterAgreementVariableSet  ***********************/
	class MasterAgreementVariableSetImpl implements MasterAgreementVariableSet {
		private final List<? extends MasterAgreementVariableSet> variableSet;
		private final String name;
		private final String value;
		
		protected MasterAgreementVariableSetImpl(MasterAgreementVariableSet.MasterAgreementVariableSetBuilder builder) {
			this.variableSet = ofNullable(builder.getVariableSet()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.name = builder.getName();
			this.value = builder.getValue();
		}
		
		@Override
		@RosettaAttribute("variableSet")
		public List<? extends MasterAgreementVariableSet> getVariableSet() {
			return variableSet;
		}
		
		@Override
		@RosettaAttribute("name")
		public String getName() {
			return name;
		}
		
		@Override
		@RosettaAttribute("value")
		public String getValue() {
			return value;
		}
		
		@Override
		public MasterAgreementVariableSet build() {
			return this;
		}
		
		@Override
		public MasterAgreementVariableSet.MasterAgreementVariableSetBuilder toBuilder() {
			MasterAgreementVariableSet.MasterAgreementVariableSetBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(MasterAgreementVariableSet.MasterAgreementVariableSetBuilder builder) {
			ofNullable(getVariableSet()).ifPresent(builder::setVariableSet);
			ofNullable(getName()).ifPresent(builder::setName);
			ofNullable(getValue()).ifPresent(builder::setValue);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			MasterAgreementVariableSet _that = getType().cast(o);
		
			if (!ListEquals.listEquals(variableSet, _that.getVariableSet())) return false;
			if (!Objects.equals(name, _that.getName())) return false;
			if (!Objects.equals(value, _that.getValue())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (variableSet != null ? variableSet.hashCode() : 0);
			_result = 31 * _result + (name != null ? name.hashCode() : 0);
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MasterAgreementVariableSet {" +
				"variableSet=" + this.variableSet + ", " +
				"name=" + this.name + ", " +
				"value=" + this.value +
			'}';
		}
	}

	/*********************** Builder Implementation of MasterAgreementVariableSet  ***********************/
	class MasterAgreementVariableSetBuilderImpl implements MasterAgreementVariableSet.MasterAgreementVariableSetBuilder {
	
		protected List<MasterAgreementVariableSet.MasterAgreementVariableSetBuilder> variableSet = new ArrayList<>();
		protected String name;
		protected String value;
	
		public MasterAgreementVariableSetBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("variableSet")
		public List<? extends MasterAgreementVariableSet.MasterAgreementVariableSetBuilder> getVariableSet() {
			return variableSet;
		}
		
		public MasterAgreementVariableSet.MasterAgreementVariableSetBuilder getOrCreateVariableSet(int _index) {
		
			if (variableSet==null) {
				this.variableSet = new ArrayList<>();
			}
			MasterAgreementVariableSet.MasterAgreementVariableSetBuilder result;
			return getIndex(variableSet, _index, () -> {
						MasterAgreementVariableSet.MasterAgreementVariableSetBuilder newVariableSet = MasterAgreementVariableSet.builder();
						return newVariableSet;
					});
		}
		
		@Override
		@RosettaAttribute("name")
		public String getName() {
			return name;
		}
		
		@Override
		@RosettaAttribute("value")
		public String getValue() {
			return value;
		}
		
	
		@Override
		public MasterAgreementVariableSet.MasterAgreementVariableSetBuilder addVariableSet(MasterAgreementVariableSet variableSet) {
			if (variableSet!=null) this.variableSet.add(variableSet.toBuilder());
			return this;
		}
		
		@Override
		public MasterAgreementVariableSet.MasterAgreementVariableSetBuilder addVariableSet(MasterAgreementVariableSet variableSet, int _idx) {
			getIndex(this.variableSet, _idx, () -> variableSet.toBuilder());
			return this;
		}
		@Override 
		public MasterAgreementVariableSet.MasterAgreementVariableSetBuilder addVariableSet(List<? extends MasterAgreementVariableSet> variableSets) {
			if (variableSets != null) {
				for (MasterAgreementVariableSet toAdd : variableSets) {
					this.variableSet.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("variableSet")
		public MasterAgreementVariableSet.MasterAgreementVariableSetBuilder setVariableSet(List<? extends MasterAgreementVariableSet> variableSets) {
			if (variableSets == null)  {
				this.variableSet = new ArrayList<>();
			}
			else {
				this.variableSet = variableSets.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("name")
		public MasterAgreementVariableSet.MasterAgreementVariableSetBuilder setName(String name) {
			this.name = name==null?null:name;
			return this;
		}
		@Override
		@RosettaAttribute("value")
		public MasterAgreementVariableSet.MasterAgreementVariableSetBuilder setValue(String value) {
			this.value = value==null?null:value;
			return this;
		}
		
		@Override
		public MasterAgreementVariableSet build() {
			return new MasterAgreementVariableSet.MasterAgreementVariableSetImpl(this);
		}
		
		@Override
		public MasterAgreementVariableSet.MasterAgreementVariableSetBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MasterAgreementVariableSet.MasterAgreementVariableSetBuilder prune() {
			variableSet = variableSet.stream().filter(b->b!=null).<MasterAgreementVariableSet.MasterAgreementVariableSetBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getVariableSet()!=null && getVariableSet().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getName()!=null) return true;
			if (getValue()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MasterAgreementVariableSet.MasterAgreementVariableSetBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			MasterAgreementVariableSet.MasterAgreementVariableSetBuilder o = (MasterAgreementVariableSet.MasterAgreementVariableSetBuilder) other;
			
			merger.mergeRosetta(getVariableSet(), o.getVariableSet(), this::getOrCreateVariableSet);
			
			merger.mergeBasic(getName(), o.getName(), this::setName);
			merger.mergeBasic(getValue(), o.getValue(), this::setValue);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			MasterAgreementVariableSet _that = getType().cast(o);
		
			if (!ListEquals.listEquals(variableSet, _that.getVariableSet())) return false;
			if (!Objects.equals(name, _that.getName())) return false;
			if (!Objects.equals(value, _that.getValue())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (variableSet != null ? variableSet.hashCode() : 0);
			_result = 31 * _result + (name != null ? name.hashCode() : 0);
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MasterAgreementVariableSetBuilder {" +
				"variableSet=" + this.variableSet + ", " +
				"name=" + this.name + ", " +
				"value=" + this.value +
			'}';
		}
	}
}
