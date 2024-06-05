package cdm.event.common;

import cdm.event.common.CorporateAction;
import cdm.event.common.CorporateAction.CorporateActionBuilder;
import cdm.event.common.CorporateAction.CorporateActionBuilderImpl;
import cdm.event.common.CorporateAction.CorporateActionImpl;
import cdm.event.common.CorporateActionTypeEnum;
import cdm.event.common.meta.CorporateActionMeta;
import cdm.product.template.Product;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.lib.records.Date;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Specifies the relevant data regarding a corporate action
 * @version ${project.version}
 */
@RosettaDataType(value="CorporateAction", builder=CorporateAction.CorporateActionBuilderImpl.class, version="${project.version}")
public interface CorporateAction extends RosettaModelObject {

	CorporateActionMeta metaData = new CorporateActionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The type of corporate action taking place.
	 */
	CorporateActionTypeEnum getCorporateActionType();
	/**
	 * The date on which the corporate action is known to have taken place.
	 */
	Date getExDate();
	/**
	 * The date on which resulting from the corporate action are delivered.
	 */
	Date getPayDate();
	/**
	 * The entity impacted by the corporate action.
	 */
	Product getUnderlier();

	/*********************** Build Methods  ***********************/
	CorporateAction build();
	
	CorporateAction.CorporateActionBuilder toBuilder();
	
	static CorporateAction.CorporateActionBuilder builder() {
		return new CorporateAction.CorporateActionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CorporateAction> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CorporateAction> getType() {
		return CorporateAction.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("corporateActionType"), CorporateActionTypeEnum.class, getCorporateActionType(), this);
		processor.processBasic(path.newSubPath("exDate"), Date.class, getExDate(), this);
		processor.processBasic(path.newSubPath("payDate"), Date.class, getPayDate(), this);
		processRosetta(path.newSubPath("underlier"), processor, Product.class, getUnderlier());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CorporateActionBuilder extends CorporateAction, RosettaModelObjectBuilder {
		Product.ProductBuilder getOrCreateUnderlier();
		Product.ProductBuilder getUnderlier();
		CorporateAction.CorporateActionBuilder setCorporateActionType(CorporateActionTypeEnum corporateActionType);
		CorporateAction.CorporateActionBuilder setExDate(Date exDate);
		CorporateAction.CorporateActionBuilder setPayDate(Date payDate);
		CorporateAction.CorporateActionBuilder setUnderlier(Product underlier);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("corporateActionType"), CorporateActionTypeEnum.class, getCorporateActionType(), this);
			processor.processBasic(path.newSubPath("exDate"), Date.class, getExDate(), this);
			processor.processBasic(path.newSubPath("payDate"), Date.class, getPayDate(), this);
			processRosetta(path.newSubPath("underlier"), processor, Product.ProductBuilder.class, getUnderlier());
		}
		

		CorporateAction.CorporateActionBuilder prune();
	}

	/*********************** Immutable Implementation of CorporateAction  ***********************/
	class CorporateActionImpl implements CorporateAction {
		private final CorporateActionTypeEnum corporateActionType;
		private final Date exDate;
		private final Date payDate;
		private final Product underlier;
		
		protected CorporateActionImpl(CorporateAction.CorporateActionBuilder builder) {
			this.corporateActionType = builder.getCorporateActionType();
			this.exDate = builder.getExDate();
			this.payDate = builder.getPayDate();
			this.underlier = ofNullable(builder.getUnderlier()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("corporateActionType")
		public CorporateActionTypeEnum getCorporateActionType() {
			return corporateActionType;
		}
		
		@Override
		@RosettaAttribute("exDate")
		public Date getExDate() {
			return exDate;
		}
		
		@Override
		@RosettaAttribute("payDate")
		public Date getPayDate() {
			return payDate;
		}
		
		@Override
		@RosettaAttribute("underlier")
		public Product getUnderlier() {
			return underlier;
		}
		
		@Override
		public CorporateAction build() {
			return this;
		}
		
		@Override
		public CorporateAction.CorporateActionBuilder toBuilder() {
			CorporateAction.CorporateActionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CorporateAction.CorporateActionBuilder builder) {
			ofNullable(getCorporateActionType()).ifPresent(builder::setCorporateActionType);
			ofNullable(getExDate()).ifPresent(builder::setExDate);
			ofNullable(getPayDate()).ifPresent(builder::setPayDate);
			ofNullable(getUnderlier()).ifPresent(builder::setUnderlier);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CorporateAction _that = getType().cast(o);
		
			if (!Objects.equals(corporateActionType, _that.getCorporateActionType())) return false;
			if (!Objects.equals(exDate, _that.getExDate())) return false;
			if (!Objects.equals(payDate, _that.getPayDate())) return false;
			if (!Objects.equals(underlier, _that.getUnderlier())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (corporateActionType != null ? corporateActionType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (exDate != null ? exDate.hashCode() : 0);
			_result = 31 * _result + (payDate != null ? payDate.hashCode() : 0);
			_result = 31 * _result + (underlier != null ? underlier.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CorporateAction {" +
				"corporateActionType=" + this.corporateActionType + ", " +
				"exDate=" + this.exDate + ", " +
				"payDate=" + this.payDate + ", " +
				"underlier=" + this.underlier +
			'}';
		}
	}

	/*********************** Builder Implementation of CorporateAction  ***********************/
	class CorporateActionBuilderImpl implements CorporateAction.CorporateActionBuilder {
	
		protected CorporateActionTypeEnum corporateActionType;
		protected Date exDate;
		protected Date payDate;
		protected Product.ProductBuilder underlier;
	
		public CorporateActionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("corporateActionType")
		public CorporateActionTypeEnum getCorporateActionType() {
			return corporateActionType;
		}
		
		@Override
		@RosettaAttribute("exDate")
		public Date getExDate() {
			return exDate;
		}
		
		@Override
		@RosettaAttribute("payDate")
		public Date getPayDate() {
			return payDate;
		}
		
		@Override
		@RosettaAttribute("underlier")
		public Product.ProductBuilder getUnderlier() {
			return underlier;
		}
		
		@Override
		public Product.ProductBuilder getOrCreateUnderlier() {
			Product.ProductBuilder result;
			if (underlier!=null) {
				result = underlier;
			}
			else {
				result = underlier = Product.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("corporateActionType")
		public CorporateAction.CorporateActionBuilder setCorporateActionType(CorporateActionTypeEnum corporateActionType) {
			this.corporateActionType = corporateActionType==null?null:corporateActionType;
			return this;
		}
		@Override
		@RosettaAttribute("exDate")
		public CorporateAction.CorporateActionBuilder setExDate(Date exDate) {
			this.exDate = exDate==null?null:exDate;
			return this;
		}
		@Override
		@RosettaAttribute("payDate")
		public CorporateAction.CorporateActionBuilder setPayDate(Date payDate) {
			this.payDate = payDate==null?null:payDate;
			return this;
		}
		@Override
		@RosettaAttribute("underlier")
		public CorporateAction.CorporateActionBuilder setUnderlier(Product underlier) {
			this.underlier = underlier==null?null:underlier.toBuilder();
			return this;
		}
		
		@Override
		public CorporateAction build() {
			return new CorporateAction.CorporateActionImpl(this);
		}
		
		@Override
		public CorporateAction.CorporateActionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CorporateAction.CorporateActionBuilder prune() {
			if (underlier!=null && !underlier.prune().hasData()) underlier = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCorporateActionType()!=null) return true;
			if (getExDate()!=null) return true;
			if (getPayDate()!=null) return true;
			if (getUnderlier()!=null && getUnderlier().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CorporateAction.CorporateActionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CorporateAction.CorporateActionBuilder o = (CorporateAction.CorporateActionBuilder) other;
			
			merger.mergeRosetta(getUnderlier(), o.getUnderlier(), this::setUnderlier);
			
			merger.mergeBasic(getCorporateActionType(), o.getCorporateActionType(), this::setCorporateActionType);
			merger.mergeBasic(getExDate(), o.getExDate(), this::setExDate);
			merger.mergeBasic(getPayDate(), o.getPayDate(), this::setPayDate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CorporateAction _that = getType().cast(o);
		
			if (!Objects.equals(corporateActionType, _that.getCorporateActionType())) return false;
			if (!Objects.equals(exDate, _that.getExDate())) return false;
			if (!Objects.equals(payDate, _that.getPayDate())) return false;
			if (!Objects.equals(underlier, _that.getUnderlier())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (corporateActionType != null ? corporateActionType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (exDate != null ? exDate.hashCode() : 0);
			_result = 31 * _result + (payDate != null ? payDate.hashCode() : 0);
			_result = 31 * _result + (underlier != null ? underlier.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CorporateActionBuilder {" +
				"corporateActionType=" + this.corporateActionType + ", " +
				"exDate=" + this.exDate + ", " +
				"payDate=" + this.payDate + ", " +
				"underlier=" + this.underlier +
			'}';
		}
	}
}
