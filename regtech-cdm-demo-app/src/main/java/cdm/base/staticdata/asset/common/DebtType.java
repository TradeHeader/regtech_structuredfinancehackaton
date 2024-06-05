package cdm.base.staticdata.asset.common;

import cdm.base.staticdata.asset.common.DebtClassEnum;
import cdm.base.staticdata.asset.common.DebtEconomics;
import cdm.base.staticdata.asset.common.DebtType;
import cdm.base.staticdata.asset.common.DebtType.DebtTypeBuilder;
import cdm.base.staticdata.asset.common.DebtType.DebtTypeBuilderImpl;
import cdm.base.staticdata.asset.common.DebtType.DebtTypeImpl;
import cdm.base.staticdata.asset.common.meta.DebtTypeMeta;
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
 * Specifies the type of debt instrument.
 * @version ${project.version}
 */
@RosettaDataType(value="DebtType", builder=DebtType.DebtTypeBuilderImpl.class, version="${project.version}")
public interface DebtType extends RosettaModelObject {

	DebtTypeMeta metaData = new DebtTypeMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the characteristics of a debt instrument.
	 */
	DebtClassEnum getDebtClass();
	/**
	 * Specifies selected financial terms of a debt instrument.
	 */
	List<? extends DebtEconomics> getDebtEconomics();

	/*********************** Build Methods  ***********************/
	DebtType build();
	
	DebtType.DebtTypeBuilder toBuilder();
	
	static DebtType.DebtTypeBuilder builder() {
		return new DebtType.DebtTypeBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends DebtType> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends DebtType> getType() {
		return DebtType.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("debtClass"), DebtClassEnum.class, getDebtClass(), this);
		processRosetta(path.newSubPath("debtEconomics"), processor, DebtEconomics.class, getDebtEconomics());
	}
	

	/*********************** Builder Interface  ***********************/
	interface DebtTypeBuilder extends DebtType, RosettaModelObjectBuilder {
		DebtEconomics.DebtEconomicsBuilder getOrCreateDebtEconomics(int _index);
		List<? extends DebtEconomics.DebtEconomicsBuilder> getDebtEconomics();
		DebtType.DebtTypeBuilder setDebtClass(DebtClassEnum debtClass);
		DebtType.DebtTypeBuilder addDebtEconomics(DebtEconomics debtEconomics0);
		DebtType.DebtTypeBuilder addDebtEconomics(DebtEconomics debtEconomics1, int _idx);
		DebtType.DebtTypeBuilder addDebtEconomics(List<? extends DebtEconomics> debtEconomics2);
		DebtType.DebtTypeBuilder setDebtEconomics(List<? extends DebtEconomics> debtEconomics3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("debtClass"), DebtClassEnum.class, getDebtClass(), this);
			processRosetta(path.newSubPath("debtEconomics"), processor, DebtEconomics.DebtEconomicsBuilder.class, getDebtEconomics());
		}
		

		DebtType.DebtTypeBuilder prune();
	}

	/*********************** Immutable Implementation of DebtType  ***********************/
	class DebtTypeImpl implements DebtType {
		private final DebtClassEnum debtClass;
		private final List<? extends DebtEconomics> debtEconomics;
		
		protected DebtTypeImpl(DebtType.DebtTypeBuilder builder) {
			this.debtClass = builder.getDebtClass();
			this.debtEconomics = ofNullable(builder.getDebtEconomics()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("debtClass")
		public DebtClassEnum getDebtClass() {
			return debtClass;
		}
		
		@Override
		@RosettaAttribute("debtEconomics")
		public List<? extends DebtEconomics> getDebtEconomics() {
			return debtEconomics;
		}
		
		@Override
		public DebtType build() {
			return this;
		}
		
		@Override
		public DebtType.DebtTypeBuilder toBuilder() {
			DebtType.DebtTypeBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(DebtType.DebtTypeBuilder builder) {
			ofNullable(getDebtClass()).ifPresent(builder::setDebtClass);
			ofNullable(getDebtEconomics()).ifPresent(builder::setDebtEconomics);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DebtType _that = getType().cast(o);
		
			if (!Objects.equals(debtClass, _that.getDebtClass())) return false;
			if (!ListEquals.listEquals(debtEconomics, _that.getDebtEconomics())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (debtClass != null ? debtClass.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (debtEconomics != null ? debtEconomics.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DebtType {" +
				"debtClass=" + this.debtClass + ", " +
				"debtEconomics=" + this.debtEconomics +
			'}';
		}
	}

	/*********************** Builder Implementation of DebtType  ***********************/
	class DebtTypeBuilderImpl implements DebtType.DebtTypeBuilder {
	
		protected DebtClassEnum debtClass;
		protected List<DebtEconomics.DebtEconomicsBuilder> debtEconomics = new ArrayList<>();
	
		public DebtTypeBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("debtClass")
		public DebtClassEnum getDebtClass() {
			return debtClass;
		}
		
		@Override
		@RosettaAttribute("debtEconomics")
		public List<? extends DebtEconomics.DebtEconomicsBuilder> getDebtEconomics() {
			return debtEconomics;
		}
		
		public DebtEconomics.DebtEconomicsBuilder getOrCreateDebtEconomics(int _index) {
		
			if (debtEconomics==null) {
				this.debtEconomics = new ArrayList<>();
			}
			DebtEconomics.DebtEconomicsBuilder result;
			return getIndex(debtEconomics, _index, () -> {
						DebtEconomics.DebtEconomicsBuilder newDebtEconomics = DebtEconomics.builder();
						return newDebtEconomics;
					});
		}
		
	
		@Override
		@RosettaAttribute("debtClass")
		public DebtType.DebtTypeBuilder setDebtClass(DebtClassEnum debtClass) {
			this.debtClass = debtClass==null?null:debtClass;
			return this;
		}
		@Override
		public DebtType.DebtTypeBuilder addDebtEconomics(DebtEconomics debtEconomics) {
			if (debtEconomics!=null) this.debtEconomics.add(debtEconomics.toBuilder());
			return this;
		}
		
		@Override
		public DebtType.DebtTypeBuilder addDebtEconomics(DebtEconomics debtEconomics, int _idx) {
			getIndex(this.debtEconomics, _idx, () -> debtEconomics.toBuilder());
			return this;
		}
		@Override 
		public DebtType.DebtTypeBuilder addDebtEconomics(List<? extends DebtEconomics> debtEconomicss) {
			if (debtEconomicss != null) {
				for (DebtEconomics toAdd : debtEconomicss) {
					this.debtEconomics.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("debtEconomics")
		public DebtType.DebtTypeBuilder setDebtEconomics(List<? extends DebtEconomics> debtEconomicss) {
			if (debtEconomicss == null)  {
				this.debtEconomics = new ArrayList<>();
			}
			else {
				this.debtEconomics = debtEconomicss.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public DebtType build() {
			return new DebtType.DebtTypeImpl(this);
		}
		
		@Override
		public DebtType.DebtTypeBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DebtType.DebtTypeBuilder prune() {
			debtEconomics = debtEconomics.stream().filter(b->b!=null).<DebtEconomics.DebtEconomicsBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getDebtClass()!=null) return true;
			if (getDebtEconomics()!=null && getDebtEconomics().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DebtType.DebtTypeBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			DebtType.DebtTypeBuilder o = (DebtType.DebtTypeBuilder) other;
			
			merger.mergeRosetta(getDebtEconomics(), o.getDebtEconomics(), this::getOrCreateDebtEconomics);
			
			merger.mergeBasic(getDebtClass(), o.getDebtClass(), this::setDebtClass);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DebtType _that = getType().cast(o);
		
			if (!Objects.equals(debtClass, _that.getDebtClass())) return false;
			if (!ListEquals.listEquals(debtEconomics, _that.getDebtEconomics())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (debtClass != null ? debtClass.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (debtEconomics != null ? debtEconomics.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DebtTypeBuilder {" +
				"debtClass=" + this.debtClass + ", " +
				"debtEconomics=" + this.debtEconomics +
			'}';
		}
	}
}
