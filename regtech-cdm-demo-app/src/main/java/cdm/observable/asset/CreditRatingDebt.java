package cdm.observable.asset;

import cdm.observable.asset.CreditRatingDebt;
import cdm.observable.asset.CreditRatingDebt.CreditRatingDebtBuilder;
import cdm.observable.asset.CreditRatingDebt.CreditRatingDebtBuilderImpl;
import cdm.observable.asset.CreditRatingDebt.CreditRatingDebtImpl;
import cdm.observable.asset.MultipleDebtTypes;
import cdm.observable.asset.meta.CreditRatingDebtMeta;
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
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Specifies the credit rating debt type(s) associated with the credit rating notation and scale. When several debt types are specified, they must be qualified through an &#39;any&#39; or &#39;all&#39;.
 * @version ${project.version}
 */
@RosettaDataType(value="CreditRatingDebt", builder=CreditRatingDebt.CreditRatingDebtBuilderImpl.class, version="${project.version}")
public interface CreditRatingDebt extends RosettaModelObject {

	CreditRatingDebtMeta metaData = new CreditRatingDebtMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies when there is only one debt type. FpML doesn&#39;t specify values in relation to the associated scheme, which is hence not specified as an enumeration as part of the CDM.
	 */
	FieldWithMetaString getDebtType();
	/**
	 * Specifies if there are several debt types, alongside an &#39;any&#39; or &#39;all&#39; or all condition. As an example, Baa1 rating is required for any long term debt and deposit.
	 */
	MultipleDebtTypes getDebtTypes();

	/*********************** Build Methods  ***********************/
	CreditRatingDebt build();
	
	CreditRatingDebt.CreditRatingDebtBuilder toBuilder();
	
	static CreditRatingDebt.CreditRatingDebtBuilder builder() {
		return new CreditRatingDebt.CreditRatingDebtBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CreditRatingDebt> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CreditRatingDebt> getType() {
		return CreditRatingDebt.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("debtType"), processor, FieldWithMetaString.class, getDebtType());
		processRosetta(path.newSubPath("debtTypes"), processor, MultipleDebtTypes.class, getDebtTypes());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CreditRatingDebtBuilder extends CreditRatingDebt, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateDebtType();
		FieldWithMetaString.FieldWithMetaStringBuilder getDebtType();
		MultipleDebtTypes.MultipleDebtTypesBuilder getOrCreateDebtTypes();
		MultipleDebtTypes.MultipleDebtTypesBuilder getDebtTypes();
		CreditRatingDebt.CreditRatingDebtBuilder setDebtType(FieldWithMetaString debtType0);
		CreditRatingDebt.CreditRatingDebtBuilder setDebtTypeValue(String debtType1);
		CreditRatingDebt.CreditRatingDebtBuilder setDebtTypes(MultipleDebtTypes debtTypes);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("debtType"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getDebtType());
			processRosetta(path.newSubPath("debtTypes"), processor, MultipleDebtTypes.MultipleDebtTypesBuilder.class, getDebtTypes());
		}
		

		CreditRatingDebt.CreditRatingDebtBuilder prune();
	}

	/*********************** Immutable Implementation of CreditRatingDebt  ***********************/
	class CreditRatingDebtImpl implements CreditRatingDebt {
		private final FieldWithMetaString debtType;
		private final MultipleDebtTypes debtTypes;
		
		protected CreditRatingDebtImpl(CreditRatingDebt.CreditRatingDebtBuilder builder) {
			this.debtType = ofNullable(builder.getDebtType()).map(f->f.build()).orElse(null);
			this.debtTypes = ofNullable(builder.getDebtTypes()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("debtType")
		public FieldWithMetaString getDebtType() {
			return debtType;
		}
		
		@Override
		@RosettaAttribute("debtTypes")
		public MultipleDebtTypes getDebtTypes() {
			return debtTypes;
		}
		
		@Override
		public CreditRatingDebt build() {
			return this;
		}
		
		@Override
		public CreditRatingDebt.CreditRatingDebtBuilder toBuilder() {
			CreditRatingDebt.CreditRatingDebtBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CreditRatingDebt.CreditRatingDebtBuilder builder) {
			ofNullable(getDebtType()).ifPresent(builder::setDebtType);
			ofNullable(getDebtTypes()).ifPresent(builder::setDebtTypes);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CreditRatingDebt _that = getType().cast(o);
		
			if (!Objects.equals(debtType, _that.getDebtType())) return false;
			if (!Objects.equals(debtTypes, _that.getDebtTypes())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (debtType != null ? debtType.hashCode() : 0);
			_result = 31 * _result + (debtTypes != null ? debtTypes.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CreditRatingDebt {" +
				"debtType=" + this.debtType + ", " +
				"debtTypes=" + this.debtTypes +
			'}';
		}
	}

	/*********************** Builder Implementation of CreditRatingDebt  ***********************/
	class CreditRatingDebtBuilderImpl implements CreditRatingDebt.CreditRatingDebtBuilder {
	
		protected FieldWithMetaString.FieldWithMetaStringBuilder debtType;
		protected MultipleDebtTypes.MultipleDebtTypesBuilder debtTypes;
	
		public CreditRatingDebtBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("debtType")
		public FieldWithMetaString.FieldWithMetaStringBuilder getDebtType() {
			return debtType;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateDebtType() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (debtType!=null) {
				result = debtType;
			}
			else {
				result = debtType = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("debtTypes")
		public MultipleDebtTypes.MultipleDebtTypesBuilder getDebtTypes() {
			return debtTypes;
		}
		
		@Override
		public MultipleDebtTypes.MultipleDebtTypesBuilder getOrCreateDebtTypes() {
			MultipleDebtTypes.MultipleDebtTypesBuilder result;
			if (debtTypes!=null) {
				result = debtTypes;
			}
			else {
				result = debtTypes = MultipleDebtTypes.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("debtType")
		public CreditRatingDebt.CreditRatingDebtBuilder setDebtType(FieldWithMetaString debtType) {
			this.debtType = debtType==null?null:debtType.toBuilder();
			return this;
		}
		@Override
		public CreditRatingDebt.CreditRatingDebtBuilder setDebtTypeValue(String debtType) {
			this.getOrCreateDebtType().setValue(debtType);
			return this;
		}
		@Override
		@RosettaAttribute("debtTypes")
		public CreditRatingDebt.CreditRatingDebtBuilder setDebtTypes(MultipleDebtTypes debtTypes) {
			this.debtTypes = debtTypes==null?null:debtTypes.toBuilder();
			return this;
		}
		
		@Override
		public CreditRatingDebt build() {
			return new CreditRatingDebt.CreditRatingDebtImpl(this);
		}
		
		@Override
		public CreditRatingDebt.CreditRatingDebtBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CreditRatingDebt.CreditRatingDebtBuilder prune() {
			if (debtType!=null && !debtType.prune().hasData()) debtType = null;
			if (debtTypes!=null && !debtTypes.prune().hasData()) debtTypes = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getDebtType()!=null) return true;
			if (getDebtTypes()!=null && getDebtTypes().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CreditRatingDebt.CreditRatingDebtBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CreditRatingDebt.CreditRatingDebtBuilder o = (CreditRatingDebt.CreditRatingDebtBuilder) other;
			
			merger.mergeRosetta(getDebtType(), o.getDebtType(), this::setDebtType);
			merger.mergeRosetta(getDebtTypes(), o.getDebtTypes(), this::setDebtTypes);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CreditRatingDebt _that = getType().cast(o);
		
			if (!Objects.equals(debtType, _that.getDebtType())) return false;
			if (!Objects.equals(debtTypes, _that.getDebtTypes())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (debtType != null ? debtType.hashCode() : 0);
			_result = 31 * _result + (debtTypes != null ? debtTypes.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CreditRatingDebtBuilder {" +
				"debtType=" + this.debtType + ", " +
				"debtTypes=" + this.debtTypes +
			'}';
		}
	}
}
