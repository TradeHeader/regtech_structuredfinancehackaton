package cdm.legaldocumentation.master;

import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.base.staticdata.party.PartyRoleEnum;
import cdm.legaldocumentation.master.MasterAgreementClauseVariant;
import cdm.legaldocumentation.master.MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder;
import cdm.legaldocumentation.master.MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilderImpl;
import cdm.legaldocumentation.master.MasterAgreementClauseVariant.MasterAgreementClauseVariantImpl;
import cdm.legaldocumentation.master.MasterAgreementVariableSet;
import cdm.legaldocumentation.master.MasterAgreementVariantIdentifierEnum;
import cdm.legaldocumentation.master.meta.MasterAgreementClauseVariantMeta;
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
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Sets the details for a specific variant associated to a clause in a Master Agreement
 * @version ${project.version}
 */
@RosettaDataType(value="MasterAgreementClauseVariant", builder=MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilderImpl.class, version="${project.version}")
public interface MasterAgreementClauseVariant extends RosettaModelObject {

	MasterAgreementClauseVariantMeta metaData = new MasterAgreementClauseVariantMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Unique identifier for this variant
	 */
	MasterAgreementVariantIdentifierEnum getIdentifier();
	/**
	 * Optional textual description of the variant.
	 */
	String getName();
	/**
	 * Optional counterparty role. This can be used where a clause needs to assign a different variant to the different parties on the agreement based upon their role i.e. Party A or Party B.
	 */
	List<CounterpartyRoleEnum> getCounterparty();
	/**
	 * Optional party. This can be used where a clause needs to assign different variants to different parties who may or may not be on the agreement.
	 */
	List<PartyRoleEnum> getOtherParty();
	/**
	 * For some variants of some clauses additional details are required to work out what has been elected. This array can be used to define the name and value of these variables. Please refer to the agreement documentation for more details of the variables that are available for any clause.
	 */
	List<? extends MasterAgreementVariableSet> getVariableSet();

	/*********************** Build Methods  ***********************/
	MasterAgreementClauseVariant build();
	
	MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder toBuilder();
	
	static MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder builder() {
		return new MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends MasterAgreementClauseVariant> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends MasterAgreementClauseVariant> getType() {
		return MasterAgreementClauseVariant.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("identifier"), MasterAgreementVariantIdentifierEnum.class, getIdentifier(), this);
		processor.processBasic(path.newSubPath("name"), String.class, getName(), this);
		processor.processBasic(path.newSubPath("counterparty"), CounterpartyRoleEnum.class, getCounterparty(), this);
		processor.processBasic(path.newSubPath("otherParty"), PartyRoleEnum.class, getOtherParty(), this);
		processRosetta(path.newSubPath("variableSet"), processor, MasterAgreementVariableSet.class, getVariableSet());
	}
	

	/*********************** Builder Interface  ***********************/
	interface MasterAgreementClauseVariantBuilder extends MasterAgreementClauseVariant, RosettaModelObjectBuilder {
		MasterAgreementVariableSet.MasterAgreementVariableSetBuilder getOrCreateVariableSet(int _index);
		List<? extends MasterAgreementVariableSet.MasterAgreementVariableSetBuilder> getVariableSet();
		MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder setIdentifier(MasterAgreementVariantIdentifierEnum identifier);
		MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder setName(String name);
		MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder addCounterparty(CounterpartyRoleEnum counterparty0);
		MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder addCounterparty(CounterpartyRoleEnum counterparty1, int _idx);
		MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder addCounterparty(List<? extends CounterpartyRoleEnum> counterparty2);
		MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder setCounterparty(List<? extends CounterpartyRoleEnum> counterparty3);
		MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder addOtherParty(PartyRoleEnum otherParty0);
		MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder addOtherParty(PartyRoleEnum otherParty1, int _idx);
		MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder addOtherParty(List<? extends PartyRoleEnum> otherParty2);
		MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder setOtherParty(List<? extends PartyRoleEnum> otherParty3);
		MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder addVariableSet(MasterAgreementVariableSet variableSet0);
		MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder addVariableSet(MasterAgreementVariableSet variableSet1, int _idx);
		MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder addVariableSet(List<? extends MasterAgreementVariableSet> variableSet2);
		MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder setVariableSet(List<? extends MasterAgreementVariableSet> variableSet3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("identifier"), MasterAgreementVariantIdentifierEnum.class, getIdentifier(), this);
			processor.processBasic(path.newSubPath("name"), String.class, getName(), this);
			processor.processBasic(path.newSubPath("counterparty"), CounterpartyRoleEnum.class, getCounterparty(), this);
			processor.processBasic(path.newSubPath("otherParty"), PartyRoleEnum.class, getOtherParty(), this);
			processRosetta(path.newSubPath("variableSet"), processor, MasterAgreementVariableSet.MasterAgreementVariableSetBuilder.class, getVariableSet());
		}
		

		MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder prune();
	}

	/*********************** Immutable Implementation of MasterAgreementClauseVariant  ***********************/
	class MasterAgreementClauseVariantImpl implements MasterAgreementClauseVariant {
		private final MasterAgreementVariantIdentifierEnum identifier;
		private final String name;
		private final List<CounterpartyRoleEnum> counterparty;
		private final List<PartyRoleEnum> otherParty;
		private final List<? extends MasterAgreementVariableSet> variableSet;
		
		protected MasterAgreementClauseVariantImpl(MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder builder) {
			this.identifier = builder.getIdentifier();
			this.name = builder.getName();
			this.counterparty = ofNullable(builder.getCounterparty()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.otherParty = ofNullable(builder.getOtherParty()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.variableSet = ofNullable(builder.getVariableSet()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("identifier")
		public MasterAgreementVariantIdentifierEnum getIdentifier() {
			return identifier;
		}
		
		@Override
		@RosettaAttribute("name")
		public String getName() {
			return name;
		}
		
		@Override
		@RosettaAttribute("counterparty")
		public List<CounterpartyRoleEnum> getCounterparty() {
			return counterparty;
		}
		
		@Override
		@RosettaAttribute("otherParty")
		public List<PartyRoleEnum> getOtherParty() {
			return otherParty;
		}
		
		@Override
		@RosettaAttribute("variableSet")
		public List<? extends MasterAgreementVariableSet> getVariableSet() {
			return variableSet;
		}
		
		@Override
		public MasterAgreementClauseVariant build() {
			return this;
		}
		
		@Override
		public MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder toBuilder() {
			MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder builder) {
			ofNullable(getIdentifier()).ifPresent(builder::setIdentifier);
			ofNullable(getName()).ifPresent(builder::setName);
			ofNullable(getCounterparty()).ifPresent(builder::setCounterparty);
			ofNullable(getOtherParty()).ifPresent(builder::setOtherParty);
			ofNullable(getVariableSet()).ifPresent(builder::setVariableSet);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			MasterAgreementClauseVariant _that = getType().cast(o);
		
			if (!Objects.equals(identifier, _that.getIdentifier())) return false;
			if (!Objects.equals(name, _that.getName())) return false;
			if (!ListEquals.listEquals(counterparty, _that.getCounterparty())) return false;
			if (!ListEquals.listEquals(otherParty, _that.getOtherParty())) return false;
			if (!ListEquals.listEquals(variableSet, _that.getVariableSet())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (identifier != null ? identifier.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (name != null ? name.hashCode() : 0);
			_result = 31 * _result + (counterparty != null ? counterparty.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (otherParty != null ? otherParty.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (variableSet != null ? variableSet.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MasterAgreementClauseVariant {" +
				"identifier=" + this.identifier + ", " +
				"name=" + this.name + ", " +
				"counterparty=" + this.counterparty + ", " +
				"otherParty=" + this.otherParty + ", " +
				"variableSet=" + this.variableSet +
			'}';
		}
	}

	/*********************** Builder Implementation of MasterAgreementClauseVariant  ***********************/
	class MasterAgreementClauseVariantBuilderImpl implements MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder {
	
		protected MasterAgreementVariantIdentifierEnum identifier;
		protected String name;
		protected List<CounterpartyRoleEnum> counterparty = new ArrayList<>();
		protected List<PartyRoleEnum> otherParty = new ArrayList<>();
		protected List<MasterAgreementVariableSet.MasterAgreementVariableSetBuilder> variableSet = new ArrayList<>();
	
		public MasterAgreementClauseVariantBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("identifier")
		public MasterAgreementVariantIdentifierEnum getIdentifier() {
			return identifier;
		}
		
		@Override
		@RosettaAttribute("name")
		public String getName() {
			return name;
		}
		
		@Override
		@RosettaAttribute("counterparty")
		public List<CounterpartyRoleEnum> getCounterparty() {
			return counterparty;
		}
		
		@Override
		@RosettaAttribute("otherParty")
		public List<PartyRoleEnum> getOtherParty() {
			return otherParty;
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
		@RosettaAttribute("identifier")
		public MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder setIdentifier(MasterAgreementVariantIdentifierEnum identifier) {
			this.identifier = identifier==null?null:identifier;
			return this;
		}
		@Override
		@RosettaAttribute("name")
		public MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder setName(String name) {
			this.name = name==null?null:name;
			return this;
		}
		@Override
		public MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder addCounterparty(CounterpartyRoleEnum counterparty) {
			if (counterparty!=null) this.counterparty.add(counterparty);
			return this;
		}
		
		@Override
		public MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder addCounterparty(CounterpartyRoleEnum counterparty, int _idx) {
			getIndex(this.counterparty, _idx, () -> counterparty);
			return this;
		}
		@Override 
		public MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder addCounterparty(List<? extends CounterpartyRoleEnum> counterpartys) {
			if (counterpartys != null) {
				for (CounterpartyRoleEnum toAdd : counterpartys) {
					this.counterparty.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("counterparty")
		public MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder setCounterparty(List<? extends CounterpartyRoleEnum> counterpartys) {
			if (counterpartys == null)  {
				this.counterparty = new ArrayList<>();
			}
			else {
				this.counterparty = counterpartys.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder addOtherParty(PartyRoleEnum otherParty) {
			if (otherParty!=null) this.otherParty.add(otherParty);
			return this;
		}
		
		@Override
		public MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder addOtherParty(PartyRoleEnum otherParty, int _idx) {
			getIndex(this.otherParty, _idx, () -> otherParty);
			return this;
		}
		@Override 
		public MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder addOtherParty(List<? extends PartyRoleEnum> otherPartys) {
			if (otherPartys != null) {
				for (PartyRoleEnum toAdd : otherPartys) {
					this.otherParty.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("otherParty")
		public MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder setOtherParty(List<? extends PartyRoleEnum> otherPartys) {
			if (otherPartys == null)  {
				this.otherParty = new ArrayList<>();
			}
			else {
				this.otherParty = otherPartys.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder addVariableSet(MasterAgreementVariableSet variableSet) {
			if (variableSet!=null) this.variableSet.add(variableSet.toBuilder());
			return this;
		}
		
		@Override
		public MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder addVariableSet(MasterAgreementVariableSet variableSet, int _idx) {
			getIndex(this.variableSet, _idx, () -> variableSet.toBuilder());
			return this;
		}
		@Override 
		public MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder addVariableSet(List<? extends MasterAgreementVariableSet> variableSets) {
			if (variableSets != null) {
				for (MasterAgreementVariableSet toAdd : variableSets) {
					this.variableSet.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("variableSet")
		public MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder setVariableSet(List<? extends MasterAgreementVariableSet> variableSets) {
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
		public MasterAgreementClauseVariant build() {
			return new MasterAgreementClauseVariant.MasterAgreementClauseVariantImpl(this);
		}
		
		@Override
		public MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder prune() {
			variableSet = variableSet.stream().filter(b->b!=null).<MasterAgreementVariableSet.MasterAgreementVariableSetBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getIdentifier()!=null) return true;
			if (getName()!=null) return true;
			if (getCounterparty()!=null && !getCounterparty().isEmpty()) return true;
			if (getOtherParty()!=null && !getOtherParty().isEmpty()) return true;
			if (getVariableSet()!=null && getVariableSet().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder o = (MasterAgreementClauseVariant.MasterAgreementClauseVariantBuilder) other;
			
			merger.mergeRosetta(getVariableSet(), o.getVariableSet(), this::getOrCreateVariableSet);
			
			merger.mergeBasic(getIdentifier(), o.getIdentifier(), this::setIdentifier);
			merger.mergeBasic(getName(), o.getName(), this::setName);
			merger.mergeBasic(getCounterparty(), o.getCounterparty(), (Consumer<CounterpartyRoleEnum>) this::addCounterparty);
			merger.mergeBasic(getOtherParty(), o.getOtherParty(), (Consumer<PartyRoleEnum>) this::addOtherParty);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			MasterAgreementClauseVariant _that = getType().cast(o);
		
			if (!Objects.equals(identifier, _that.getIdentifier())) return false;
			if (!Objects.equals(name, _that.getName())) return false;
			if (!ListEquals.listEquals(counterparty, _that.getCounterparty())) return false;
			if (!ListEquals.listEquals(otherParty, _that.getOtherParty())) return false;
			if (!ListEquals.listEquals(variableSet, _that.getVariableSet())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (identifier != null ? identifier.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (name != null ? name.hashCode() : 0);
			_result = 31 * _result + (counterparty != null ? counterparty.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (otherParty != null ? otherParty.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (variableSet != null ? variableSet.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MasterAgreementClauseVariantBuilder {" +
				"identifier=" + this.identifier + ", " +
				"name=" + this.name + ", " +
				"counterparty=" + this.counterparty + ", " +
				"otherParty=" + this.otherParty + ", " +
				"variableSet=" + this.variableSet +
			'}';
		}
	}
}
